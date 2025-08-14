package gtb.common.metatileentities.multiblocks;

import gtb.api.render.GTBTextures;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing3;
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
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import gtb.api.recipes.GTBRecipeMaps;

public class MetaTileEntityCoker extends RecipeMapMultiblockController {

    public MetaTileEntityCoker(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.COKER_RECIPES);
    }

    public IBlockState getCasingState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING3.getState(GTBMultiblockCasing3.CasingType.ALUMINA_LINED_STEEL_CASING);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("~~~~~CCC~~~~~", "~CP~PCCCP~PC~", "C~CFCCCCCFC~C", "~CP~PCCCP~PC~", "~~~~~CSC~~~~~")
                .aisle("~~~~~CCC~~~~~", "~CPPP~~~PPPC~", "C~CFC~~~CFC~C", "~CPPP~~~PPPC~", "~~~~~CCC~~~~~")
                .aisle("~~~~~CCC~~~~~", "~C~~P~~~P~~C~", "C~CFP~~~PFC~C", "~C~~P~~~P~~C~", "~~~~~CCC~~~~~")
                .aisle("~~~~~CCC~~~~~", "~C~~P~~~P~~C~", "C~CFP~~~PFC~C", "~C~~P~~~P~~C~", "~~~~~CCC~~~~~")
                .aisle("~~~~~CCC~~~~~", "~C~~P~~~P~~C~", "C~CFP~~~PFC~C", "~C~~P~~~P~~C~", "~~~~~CCC~~~~~")
                .aisle("~~~~~CCC~~~~~", "~C~~P~~~P~~C~", "C~CFP~~~PFC~C", "~C~~P~~~P~~C~", "~~~~~CCC~~~~~")
                .aisle("~~~~~CCC~~~~~", "~C~~PC~CP~~C~", "C~CFP~~~PFC~C", "~C~~PC~CP~~C~", "~~~~~CCC~~~~~")
                .aisle("~~~~~~F~~~~~~", "~C~~~FCF~~~C~", "C~CFFCCCFFC~C", "~C~~~FCF~~~C~", "~~~~~~F~~~~~~")
                .aisle("~~~~~~~~~~~~~", "~C~~~~~~~~~C~", "C~C~~~~~~~C~C", "~C~~~~~~~~~C~", "~~~~~~~~~~~~~")
                .aisle("~~~~~~~~~~~~~", "~~~~~~~~~~~~~", "~C~~~~~~~~~C~", "~~~~~~~~~~~~~", "~~~~~~~~~~~~~")
                .where('S', selfPredicate())
                .where('G', states(Blocks.GLASS.getDefaultState()))
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(130)
                        .or(autoAbilities()))
                .where('F', frames(Materials.Steel))
                .where('P', states(MetaBlocks.BOILER_CASING.getState((BoilerCasingType.STEEL_PIPE))))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTBTextures.ALUMINA_LINED_STEEL_CASING_OVERLAY;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityCoker(metaTileEntityId);
    }
}
