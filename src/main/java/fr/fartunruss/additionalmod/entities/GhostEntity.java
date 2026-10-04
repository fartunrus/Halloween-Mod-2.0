package fr.fartunruss.additionalmod.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class GhostEntity extends MonsterEntity {
    protected static final DataParameter<Byte> GHOST_FLAGS = EntityDataManager.createKey(GhostEntity.class, DataSerializers.BYTE);
    @Nullable
    private BlockPos boundOrigin;

    public GhostEntity(EntityType<? extends GhostEntity> p_i50190_1_, World p_i50190_2_) {
        super(p_i50190_1_, p_i50190_2_);
        this.moveController = new MoveHelperController(this);
        this.experienceValue = 20;
    }

    public void move(MoverType typeIn, Vector3d pos) {
        super.move(typeIn, pos);
        this.doBlockCollisions();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        this.noClip = true;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new ChargeAttackGoal());
        this.goalSelector.addGoal(8, new MoveRandomGoal());
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 50.0F, 2.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.addGoal(2, new CopyOwnerTargetGoal(this));
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(48.0F);
        this.targetSelector.addGoal(3, new AttackNearestGoal(this, PlayerEntity.class, 4, true, false, (Predicate<LivingEntity>)null));
    }

    class AttackNearestGoal extends NearestAttackableTargetGoal<PlayerEntity> {

        public AttackNearestGoal(MobEntity goalOwnerIn, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, @Nullable Predicate<LivingEntity> targetPredicate) {
            super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight,nearbyOnlyIn, targetPredicate);
        }
        protected AxisAlignedBB getTargetableArea(double targetDistance) {
            return this.goalOwner.getBoundingBox().grow(targetDistance, targetDistance, targetDistance);
        }
    }
    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MAX_HEALTH, 30.0D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(GHOST_FLAGS, (byte)0);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("BoundX")) {
            this.boundOrigin = new BlockPos(compound.getInt("BoundX"), compound.getInt("BoundY"), compound.getInt("BoundZ"));
        }
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.boundOrigin != null) {
            compound.putInt("BoundX", this.boundOrigin.getX());
            compound.putInt("BoundY", this.boundOrigin.getY());
            compound.putInt("BoundZ", this.boundOrigin.getZ());
        }
    }
    @Nullable
    public BlockPos getBoundOrigin() {
        return this.boundOrigin;
    }

    public void setBoundOrigin(@Nullable BlockPos boundOriginIn) {
        this.boundOrigin = boundOriginIn;
    }

    private boolean getGhostFlag(int mask) {
        int i = this.dataManager.get(GHOST_FLAGS);
        return (i & mask) != 0;
    }

    private void setGhostFlag(int mask, boolean value) {
        int i = this.dataManager.get(GHOST_FLAGS);
        if (value) {
            i = i | mask;
        } else {
            i = i & ~mask;
        }

        this.dataManager.set(GHOST_FLAGS, (byte)(i & 255));
    }

    public boolean isCharging() {
        return this.getGhostFlag(1);
    }

    public void setCharging(boolean charging) {
        this.setGhostFlag(1, charging);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VEX_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VEX_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_VEX_HURT;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness() {
        return 1.0F;
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
       // this.setEquipmentBasedOnDifficulty(difficultyIn);
        this.setEnchantmentBasedOnDifficulty(difficultyIn);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    //protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        //this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
        //this.setDropChance(EquipmentSlotType.MAINHAND, 0.0F);
    //}

    class ChargeAttackGoal extends Goal {
        public ChargeAttackGoal() {
            this.setMutexFlags(EnumSet.of(Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (GhostEntity.this.getAttackTarget() != null && !GhostEntity.this.getMoveHelper().isUpdating() && GhostEntity.this.rand.nextInt(7) == 0) {
                return GhostEntity.this.getDistanceSq(GhostEntity.this.getAttackTarget()) > 4.0D;
            } else {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return GhostEntity.this.getMoveHelper().isUpdating() && GhostEntity.this.isCharging() && GhostEntity.this.getAttackTarget() != null && GhostEntity.this.getAttackTarget().isAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            LivingEntity livingentity = GhostEntity.this.getAttackTarget();
            Vector3d vector3d = livingentity.getEyePosition(1.0F);
            GhostEntity.this.moveController.setMoveTo(vector3d.x, vector3d.y, vector3d.z, 1.0D);
            GhostEntity.this.setCharging(true);
            GhostEntity.this.playSound(SoundEvents.ENTITY_VEX_CHARGE, 1.0F, 1.0F);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            GhostEntity.this.setCharging(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = GhostEntity.this.getAttackTarget();
            if (GhostEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                GhostEntity.this.attackEntityAsMob(livingentity);
                GhostEntity.this.setCharging(false);
            } else {
                double d0 = GhostEntity.this.getDistanceSq(livingentity);
                if (d0 < 9.0D) {
                    Vector3d vector3d = livingentity.getEyePosition(1.0F);
                    GhostEntity.this.moveController.setMoveTo(vector3d.x, vector3d.y, vector3d.z, 1.0D);
                }
            }

        }
    }

    class CopyOwnerTargetGoal extends TargetGoal {
        private final EntityPredicate field_220803_b = (new EntityPredicate()).setIgnoresLineOfSight().setUseInvisibilityCheck();

        public CopyOwnerTargetGoal(CreatureEntity creature) {
            super(creature, false);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return GhostEntity.this.getAttackTarget() != null && this.isSuitableTarget(GhostEntity.this.getAttackTarget(), this.field_220803_b);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting(LivingEntity entity) {
            GhostEntity.this.setAttackTarget(entity);
            super.startExecuting();
        }
    }

    class MoveHelperController extends MovementController {
        public MoveHelperController(GhostEntity vex) {
            super(vex);
        }

        public void tick() {
            if (this.action == Action.MOVE_TO) {
                Vector3d vector3d = new Vector3d(this.posX - GhostEntity.this.getPosX(), this.posY - GhostEntity.this.getPosY(), this.posZ - GhostEntity.this.getPosZ());
                double d0 = vector3d.length();
                if (d0 < GhostEntity.this.getBoundingBox().getAverageEdgeLength()) {
                    this.action = Action.WAIT;
                    GhostEntity.this.setMotion(GhostEntity.this.getMotion().scale(0.5D));
                } else {
                    GhostEntity.this.setMotion(GhostEntity.this.getMotion().add(vector3d.scale(this.speed * 0.05D / d0)));
                    if (GhostEntity.this.getAttackTarget() == null) {
                        Vector3d vector3d1 = GhostEntity.this.getMotion();
                        GhostEntity.this.rotationYaw = -((float) MathHelper.atan2(vector3d1.x, vector3d1.z)) * (180F / (float)Math.PI);
                        GhostEntity.this.renderYawOffset = GhostEntity.this.rotationYaw;
                    } else {
                        double d2 = GhostEntity.this.getAttackTarget().getPosX() - GhostEntity.this.getPosX();
                        double d1 = GhostEntity.this.getAttackTarget().getPosZ() - GhostEntity.this.getPosZ();
                        GhostEntity.this.rotationYaw = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                        GhostEntity.this.renderYawOffset = GhostEntity.this.rotationYaw;
                    }
                }

            }
        }
    }

    class MoveRandomGoal extends Goal {
        public MoveRandomGoal() {
            this.setMutexFlags(EnumSet.of(Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return !GhostEntity.this.getMoveHelper().isUpdating() && GhostEntity.this.rand.nextInt(7) == 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            BlockPos blockpos = GhostEntity.this.getBoundOrigin();
            if (blockpos == null) {
                blockpos = GhostEntity.this.getPosition();
            }

            for(int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.add(GhostEntity.this.rand.nextInt(15) - 7, GhostEntity.this.rand.nextInt(11) - 5, GhostEntity.this.rand.nextInt(15) - 7);
                if (GhostEntity.this.world.isAirBlock(blockpos1)) {
                    GhostEntity.this.moveController.setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);
                    if (GhostEntity.this.getAttackTarget() == null) {
                        GhostEntity.this.getLookController().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }
                    break;
                }
            }

        }
    }
}