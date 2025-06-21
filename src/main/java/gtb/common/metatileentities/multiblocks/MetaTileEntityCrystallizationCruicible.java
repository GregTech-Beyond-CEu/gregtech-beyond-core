package gtb.common.metatileentities.multiblocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;

import gtb.api.recipes.GTBRecipeMaps;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing2;

public class MetaTileEntityCrystallizationCruicible extends RecipeMapMultiblockController {

    public MetaTileEntityCrystallizationCruicible(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.CRUCIBLE_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle(
                        "FCCCF",
                        "CHHHC",
                        "CHHHC",
                        "CHHHC",
                        "FCSCF")
                .aisle(
                        "CCCCC",
                        "CQQQC",
                        "CQQQC",
                        "CQQQC",
                        "CCCCC")
                .aisle(
                        "CCCCC",
                        "CQQQC",
                        "CQ~QC",
                        "CQQQC",
                        "CCCCC")
                .aisle(
                        "CCCCC",
                        "CQQQC",
                        "CQ~QC",
                        "CQQQC",
                        "CCCCC")
                .aisle(
                        "CCCCC",
                        "CQQQC",
                        "CQ~QC",
                        "CQQQC",
                        "CCCCC")
                .where('S', selfPredicate())
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(90)
                        .or(autoAbilities()))
                .where('Q',
                        states(GTBMetaBlocks.GTB_MULTIBLOCK_CASING2
                                .getState(GTBMultiblockCasing2.CasingType.FUSED_QUARTZ_BLOCK)))
                .where('H', states(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.NICHROME)))
                .where('F', frames(Materials.Titanium))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.POLARIZER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCrystallizationCruicible(metaTileEntityId);
    }
}
