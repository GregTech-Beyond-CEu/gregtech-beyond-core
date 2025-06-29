package gtb.common.metatileentities.multiblocks;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;

import gregicality.multiblocks.api.unification.GCYMMaterials;

import gtb.api.recipes.GTBRecipeMaps;

public class MetaTileEntityBurnerReactor extends RecipeMapMultiblockController {

    public MetaTileEntityBurnerReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.BURNER_REACTOR_RECIPES);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.FRONT, RelativeDirection.UP)
                .aisle("F   F", "  X  ", " XXX ", "  X  ", "F   F")
                .aisle("F X F", " XCX ", "XCCCX", " XCX ", "F X F")
                .aisle("FXSXF", "XCCCX", "XK#KX", "XCCCX", "FXXXF")
                .aisle("F X F", " XCX ", "XC#CX", " XCX ", "F X F")
                .aisle("F   F", "  X  ", " XCX ", "  X  ", "F   F")
                .aisle("     ", "  X  ", " XMX ", "  X  ", "     ")
                .where('S', selfPredicate())
                .where('X',
                        states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
                                .setMinGlobalLimited(25)
                                .or(autoAbilities()))
                .where('F',
                        states(MetaBlocks.FRAMES.get(GCYMMaterials.MaragingSteel300)
                                .getBlock(GCYMMaterials.MaragingSteel300)))
                .where('C', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)))
                .where('K',
                        states(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.NICHROME)))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where(' ', any())
                .where('#', air())
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
        return new MetaTileEntityBurnerReactor(metaTileEntityId);
    }
}
