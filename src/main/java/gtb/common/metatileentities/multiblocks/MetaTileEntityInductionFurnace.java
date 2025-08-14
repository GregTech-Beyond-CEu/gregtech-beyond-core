package gtb.common.metatileentities.multiblocks;

import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;
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
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gtb.api.recipes.GTBRecipeMaps;
import gtb.api.render.GTBTextures;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing3;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class MetaTileEntityInductionFurnace extends RecipeMapMultiblockController {

    public MetaTileEntityInductionFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.INDUCTION_FURNACE_RECIPES);
    }

    public IBlockState getCasingState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING3.getState(GTBMultiblockCasing3.CasingType.MAGNESIA_BASED_REFACTORY_BRICKS);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.DOWN, RelativeDirection.BACK)
                .aisle("~~~~~~~", "~~~~~~~", "~~~~~~~", "~~~~~~~", "~~~~~~~", "~~F~F~~", "~~F~F~~")
                .aisle("~~FFF~~", "~~~~~~~", "~~~~~~~", "~~~~~~~", "~~FFF~~", "~~CCC~~", "~~CCC~~")
                .aisle("~FZZZF~", "~~CCC~~", "~~CCC~~", "~~CCC~~", "~FCCCF~", "FC~~~CF", "FCCCCCF")
                .aisle("~FZZZF~", "~~C~C~~", "~~C~C~~", "~~C~C~~", "~FC~CF~", "~C~~~C~", "~CCCCC~")
                .aisle("~FZZZF~", "~~CCC~~", "~~CCC~~", "~~CCC~~", "~FCCCF~", "FC~~~CF", "FCCCCCF")
                .aisle("~~FFF~~", "~~~~~~~", "~~~~~~~", "~~~~~~~", "~~FFF~~", "~~CSC~~", "~~CCC~~")
                .aisle("~~~~~~~", "~~~~~~~", "~~~~~~~", "~~~~~~~", "~~~~~~~", "~~F~F~~", "~~F~F~~")
                .where('S', selfPredicate())
                .where('Z',
                        states(GTBMetaBlocks.GTB_MULTIBLOCK_CASING3.getState(GTBMultiblockCasing3.CasingType.INDUSTRIAL_EXHAUST_VENT)))
                .where('~', any())
                .where('F', frames(Materials.Steel))
                .where('C', states(getCasingState()).setMinGlobalLimited(55)
                        .or(autoAbilities()))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTBTextures.MAGNESIA_BASED_REFRACTORY_BRICKS_OVERLAY;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityInductionFurnace(metaTileEntityId);
    }
}
