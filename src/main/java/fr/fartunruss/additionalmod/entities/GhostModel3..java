// Made with Blockbench 4.4.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports
/*

public class GhostModel extends EntityModel<Entity> {
	private final ModelRenderer arms;
	private final ModelRenderer left_arm2_15_15_r1;
	private final ModelRenderer clothes;
	private final ModelRenderer cloth_r1;
	private final ModelRenderer bb_main;
	private final ModelRenderer head_r1;

	public GhostModel() {
		textureWidth = 16;
		textureHeight = 16;

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		left_arm2_15_15_r1 = new ModelRenderer(this);
		left_arm2_15_15_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		arms.addChild(left_arm2_15_15_r1);
		setRotationAngle(left_arm2_15_15_r1, 0.0F, 0.0F, 0.0F);
		left_arm2_15_15_r1.setTextureOffset(0, 0).addBox(-3.0F, -5.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		left_arm2_15_15_r1.setTextureOffset(0, 0).addBox(1.0F, -5.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		clothes = new ModelRenderer(this);
		clothes.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		cloth_r1 = new ModelRenderer(this);
		cloth_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		clothes.addChild(cloth_r1);
		setRotationAngle(cloth_r1, 0.4363F, 0.0F, 0.0F);
		cloth_r1.setTextureOffset(0, 0).addBox(-1.0F, -2.25F, -0.05F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		cloth_r1.setTextureOffset(0, 0).addBox(-1.0F, -1.75F, 0.95F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(head_r1);
		setRotationAngle(head_r1, 0.0F, 0.0F, 0.0F);
		head_r1.setTextureOffset(0, 0).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head_r1.setTextureOffset(0, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		arms.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		clothes.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}*/