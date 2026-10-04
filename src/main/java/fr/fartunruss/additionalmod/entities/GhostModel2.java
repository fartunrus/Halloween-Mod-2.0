package fr.fartunruss.additionalmod.entities;// Made with Blockbench 4.4.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhostModel2<T extends Entity> extends SegmentedModel<T> {
	private final ModelRenderer bb_main;
	/*private final ModelRenderer left_arm2_15_15_r1;
	private final ModelRenderer right_arm2_15_15_r1;*/
	private final ModelRenderer arms;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer cloth_r1;
	private final ModelRenderer clothes;

	public GhostModel2() {
		textureWidth = 64;
		textureHeight = 64;

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, 24.0F, 0.0F);

		/*
		left_arm2_15_15_r1 = new ModelRenderer(this);
		left_arm2_15_15_r1.setRotationPoint(-6.0F, -18.0F, 0.0F);
		arms.addChild(left_arm2_15_15_r1);
		setRotationAngle(left_arm2_15_15_r1, 0.0F, 0.0F, -1.5708F);
		left_arm2_15_15_r1.setTextureOffset(28, 32).addBox(-8.0F, -2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);

		right_arm2_15_15_r1 = new ModelRenderer(this);
		right_arm2_15_15_r1.setRotationPoint(6.0F, -18.0F, 0.0F);
		arms.addChild(right_arm2_15_15_r1);
		setRotationAngle(right_arm2_15_15_r1, 0.0F, 0.0F, 1.5708F);
		right_arm2_15_15_r1.setTextureOffset(0, 36).addBox(0.0F, -2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		*/
		clothes = new ModelRenderer(this);
		clothes.setRotationPoint(0.0F, 24.0F, 0.0F);


		cloth_r1 = new ModelRenderer(this);
		cloth_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		clothes.addChild(cloth_r1);
		setRotationAngle(cloth_r1, 0.4363F, 0.0F, 0.0F);
		cloth_r1.setTextureOffset(32, 0).addBox(-4.0F, -9.0F, -0.2F, 8.0F, 8.0F, 4.0F, 0.0F, false);
		cloth_r1.setTextureOffset(28, 16).addBox(-4.0F, -7.0F, 3.8F, 8.0F, 8.0F, 4.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);


		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -20.0F, 0.0F);
		bb_main.addChild(head);
		setRotationAngle(head, 0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 20).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(body);
		setRotationAngle(body, 0.0F, 0.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-4.0F, -20.0F, -4.0F, 8.0F, 12.0F, 8.0F, 0.0F, false);
	}
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		arms.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		clothes.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		/*this.left_arm2_15_15_r1.rotateAngleZ = 0;
		this.right_arm2_15_15_r1.rotateAngleZ = 0;
		this.left_arm2_15_15_r1.rotateAngleY=0;
		this.right_arm2_15_15_r1.rotateAngleY=-0;*/
	}

	/**
	 * Sets this entity's model rotation angles
	 */

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(this.head, this.body, /*this.left_arm2_15_15_r1, this.right_arm2_15_15_r1,*/ this.cloth_r1);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}