package gtb.common.metatileentities.multiblocks;

import static gregtech.api.unification.material.Materials.*;

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
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;

import gtb.api.recipes.GTBRecipeMaps;
import gtb.api.render.GTBTextures;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing;

public class MetaTileEntitySinteringOven extends RecipeMapMultiblockController {

    public MetaTileEntitySinteringOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.SINTERING_OVEN_RECIPES);
    }

    public IBlockState getCasingState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING.getState(GTBMultiblockCasing.CasingType.BASIC_STRUCTURAL_CASING);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("CCC", "FFF", "~~~", "FFF", "~~~", "FFF", "CCC")
                .aisle("CCC", "FAF", "~A~", "FAF", "~A~", "FAF", "CSC")
                .aisle("CCC", "FFF", "~~~", "FFF", "~~~", "FFF", "CCC")
                .where('S', selfPredicate())
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(10)
                        .or(autoAbilities()))
                .where('F', frames(Steel))
                .where('A', heatingCoils())
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTBTextures.SINTERING_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.MACERATOR_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySinteringOven(metaTileEntityId);
    }
}
