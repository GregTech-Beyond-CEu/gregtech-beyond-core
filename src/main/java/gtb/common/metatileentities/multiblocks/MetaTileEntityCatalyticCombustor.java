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
import gregtech.common.blocks.BlockBoilerCasing.BoilerCasingType;
import gtb.common.block.GTBMetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.recipes.RecipeMaps.CRACKING_RECIPES;
import static gtb.api.recipes.GTBRecipeMaps.CATALYTIC_COMBUSTOR_RECIPES;

public class MetaTileEntityCatalyticCombustor extends RecipeMapMultiblockController {

    public MetaTileEntityCatalyticCombustor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, CATALYTIC_COMBUSTOR_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle( "~~~~~~ZZZ",
                        "~~P~~~ZZZ",
                        "~~~~~~ZZZ",
                        "~~~~~~~~~",
                        "CCCCC~~~~",
                        "CCCCC~~P~",
                        "CCSCC~~~~")

                .aisle( "~~~~~~GGG",
                        "~~PPPPTFG",
                        "~~P~~~GTG",
                        "~~P~~~~P~",
                        "CCCCC~~P~",
                        "C~~~CPPP~",
                        "CCCCC~~~~")

                .aisle( "~~~~~~GGG",
                        "~~T~~~G~G",
                        "~~~~~~GGG",
                        "~~~~~~~~~",
                        "CCCCC~~~~",
                        "CFFFC~~~~",
                        "CCCCC~~~~")

                .aisle( "~~~~~~GGG",
                        "~~Z~~~G~G",
                        "~~~~~~GGG",
                        "~~~~~~~~~",
                        "~~~~~~~~~",
                        "~~~~~~~~~",
                        "~~~~~~~~~")

                .aisle( "~~~~~~CCC",
                        "~~P~~~C~C",
                        "~~~~~~CCC",
                        "~~~~~~~~~",
                        "~~~~~~~~~",
                        "~~~~~~~~~")

                .aisle( "~~~~~~~C~",
                        "~~F~~~CCC",
                        "~~~~~~~C~",
                        "~~~~~~~~~",
                        "~~~~~~~~~",
                        "~~~~~~~~~")
                .where('S', selfPredicate())
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(10).or(autoAbilities()))
                .where('F', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('P', states(MetaBlocks.BOILER_CASING.getState((BoilerCasingType.STEEL_PIPE))))
                .where('Z', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)))
                .where('T', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
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
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCatalyticCombustor(metaTileEntityId);
    }
}
