package gtb.common.metatileentities.multiblocks;

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
import gregtech.common.blocks.*;
import gtb.api.recipes.GTBRecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.StainlessSteel;
import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityReactionFurnace extends RecipeMapMultiblockController {

    public MetaTileEntityReactionFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.REACTION_FURNACE_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("~F~F~", "~~~~~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~PBP~", "~~B~~", "~FBF~")
                .aisle("~FPF~", "~~P~~", "CCCCC", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "CCCCC", "~B~B~", "~B~B~", "~BZB~")
                .aisle("~PZP~", "~P~P~", "CCCCC", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "CCCCC", "~~P~~", "~~P~~", "~~P~~")
                .aisle("~~P~~", "~~P~~", "CCCCC", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "CCSCC", "~~~~~", "~~~~~", "~~~~~")
                .aisle("~~~~~", "~~~~~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~~~~~", "~~~~~", "~~~~~")
                .aisle("~~~~~", "~~~~~", "~~~~~", "~~~~~", "~F~F~", "~~P~~", "~F~F~", "~~~~~", "~F~F~", "~~~~~", "~~~~~", "~~~~~", "~~~~~", "~~~~~")
                .aisle("~~~~~", "~~~~~", "~~~~~", "~~N~~", "~FNF~", "~~P~~", "~FNF~", "~~N~~", "~FNF~", "~~N~~", "~~~~~", "~~~~~", "~~~~~", "~~~~~")
                .aisle("~~~~~", "~~~~~", "~~N~~", "~N~N~", "~J~J~", "~N~N~", "~J~J~", "~N~N~", "~J~J~", "~N~N~", "~~N~~", "~~~~~", "~~~~~", "~~~~~")
                .aisle("~~~~~", "~~~~~", "~~~~~", "~~N~~", "~~N~~", "~~N~~", "~~N~~", "~~N~~", "~~N~~", "~~N~~", "~~~~~", "~~~~~", "~~~~~", "~~~~~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(90)
                        .or(autoAbilities()))
                .where('F', frames(Steel))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('N', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)))
                .where('Z', states(MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.STEEL_FIREBOX)))
                .where('B', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)))
                .where('J', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STAINLESS_STEEL_GEARBOX)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.HEAT_PROOF_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.BLAST_FURNACE_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityReactionFurnace(metaTileEntityId);
    }
}
