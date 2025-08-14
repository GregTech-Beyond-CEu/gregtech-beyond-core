package gtb.common.metatileentities.multiblocks;

import gtb.api.render.GTBTextures;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing3;
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
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import gtb.api.recipes.GTBRecipeMaps;

public class MetaTileEntityCatalyticReformationUnit extends RecipeMapMultiblockController {

    public MetaTileEntityCatalyticReformationUnit(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.CATALYTIC_REFORMATION_UNIT_RECIPES);
    }

    public IBlockState getCasingState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING3.getState(GTBMultiblockCasing3.CasingType.INCOLOY_600_LINED_STEEL_CASING);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("F###F", "XXXPX", "XXXPX", "XXXPX")
                .aisle("#####", "XXXPX", "X###X", "XXXPX")
                .aisle("F###F", "XXXPX", "XXSPX", "XXXPX")
                .where('S', selfPredicate())
                .where('#', any())
                .where('X', states(getCasingState()).setMinGlobalLimited(22)
                        .or(autoAbilities()))
                .where('F', frames(Materials.StainlessSteel))
                .where('P', states(GTBMetaBlocks.GTB_MULTIBLOCK_CASING3.getState(GTBMultiblockCasing3.CasingType.INCOLOY_600_LINED_STEEL_PIPE_CASING)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTBTextures.INCOLOY_600_LINED_STEEL_CASING_OVERLAY;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCatalyticReformationUnit(metaTileEntityId);
    }
}
