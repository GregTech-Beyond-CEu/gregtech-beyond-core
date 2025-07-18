package gtb.common.metatileentities.multiblocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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
import gregtech.common.blocks.BlockBoilerCasing.BoilerCasingType;
import gregtech.common.blocks.MetaBlocks;

import gtb.api.recipes.GTBRecipeMaps;

public class MetaTileEntityFermentationVat extends RecipeMapMultiblockController {

    public MetaTileEntityFermentationVat(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.FERMENTATION_VAT_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.MACHINE_CASING.getDefaultState();
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("~~~~~", "~F~F~", "~~~~~", "~F~F~", "~~~~~")
                .aisle("~~P~~", "~WWW~", "PWWWP", "~WWW~", "~~S~~")
                .aisle("~~P~~", "PGGGP", "PG~GP", "PGGGP", "~~P~~")
                .aisle("~~P~~", "~GGG~", "PG~GP", "~GGG~", "~~P~~")
                .aisle("~~P~~", "PGGGP", "PG~GP", "PGGGP", "~~P~~")
                .aisle("~~P~~", "~WWW~", "PWWWP", "~WWW~", "~~P~~")
                .aisle("~~~~~", "~~P~~", "~PPP~", "~~P~~", "~~~~~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('W', states(getCasingState()).setMinGlobalLimited(10)
                        .or(autoAbilities()))
                .where('F', frames(Materials.Steel))
                .where('P', states(MetaBlocks.BOILER_CASING.getState((BoilerCasingType.STEEL_PIPE))))
                .where('G', states(Blocks.GLASS.getDefaultState()))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
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
        return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityFermentationVat(metaTileEntityId);
    }
}
