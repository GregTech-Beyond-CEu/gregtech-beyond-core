package gtb.api.pattern;


import gregtech.api.block.VariantActiveBlock;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.PatternStringError;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.BlockInfo;
import gregtech.common.blocks.BlockFireboxCasing;
import gregtech.common.blocks.MetaBlocks;
import gtb.api.block.ITierGlassBlockState;
import gtb.api.block.impl.WrappedIntTier;
import gtb.api.metatileentity.multiblock.GTBMultiblockAbility;
import gtb.api.pattern.predicates.TierStateTraceabilityPredicate;
import gtb.api.pattern.predicates.TierTraceabilityPredicate;
import gtb.api.utils.GTBUniverUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static gtb.api.GTBAPI.*;

public class GTBTraceabilityPredicate {

    //  Same Block State Traceability Predicates
    public static Supplier<TraceabilityPredicate> GTB_GLASS = () -> new TierStateTraceabilityPredicate(MAP_GLASS, "Glass", "gtb.multiblock.pattern.error.glasses");
    public static Supplier<TraceabilityPredicate> GTB_OPTICAL_GLASS = () -> new TierStateTraceabilityPredicate(MAP_GLASS,
            state -> ((ITierGlassBlockState)MAP_GLASS.get(state)).isOpticalGlass(), "Glass", "gtb.multiblock.pattern.error.glasses");
    public static Supplier<TraceabilityPredicate> GTB_GLASS_NO_OPTICAL = () -> new TierStateTraceabilityPredicate(MAP_GLASS,
            state -> !((ITierGlassBlockState)MAP_GLASS.get(state)).isOpticalGlass(), "Glass", "gtb.multiblock.pattern.error.glasses");
    public static Supplier<TraceabilityPredicate> ROTOR_HOLDER = () -> new TraceabilityPredicate(blockWorldState -> {
        TileEntity tileEntity = blockWorldState.getTileEntity();
        if (tileEntity instanceof IGregTechTileEntity) {
            List<ResourceLocation> list = MultiblockAbility.REGISTRY.get(GTBMultiblockAbility.REINFORCED_ROTOR_MULTIBLOCK_ABILITY).stream()
                    .map(mte -> mte.metaTileEntityId)
                    .collect(Collectors.toList());
            MetaTileEntity mte = ((IGregTechTileEntity)tileEntity).getMetaTileEntity();
            if (list.contains(mte.metaTileEntityId)) {
                int tier = ((ITieredMetaTileEntity) mte).getTier();
                Object currentTier = blockWorldState.getMatchContext().getOrPut("RotorHolderTier", tier);
                if (!currentTier.equals(tier)) {
                    blockWorldState.setError(new PatternStringError("gtb.multiblock.pattern.error.rotor_holder"));
                    return false;
                }
                Set<IMultiblockPart> partsFound = blockWorldState.getMatchContext().getOrCreate("MultiblockParts", HashSet::new);
                partsFound.add((IMultiblockPart) mte);
                return true;
            }
        }
        return false;
    }, () -> MultiblockAbility.REGISTRY.get(GTBMultiblockAbility.REINFORCED_ROTOR_MULTIBLOCK_ABILITY).stream()
            .sorted(Comparator.comparingInt(mte -> ((ITieredMetaTileEntity) mte).getTier()))
            .map(mte -> new BlockInfo(MetaBlocks.MACHINE.getDefaultState(), GTBUniverUtil.getTileEntity(mte)))
            .toArray(BlockInfo[]::new))
            .addTooltips("gtb.multiblock.pattern.error.rotor_holder");

    public static Supplier<TraceabilityPredicate> FIRE_BOX = () -> new TraceabilityPredicate(blockWorldState -> {
        IBlockState blockState = blockWorldState.getBlockState();
        if ((blockState.getBlock() instanceof BlockFireboxCasing BlockFireboxCasing)) {
            BlockFireboxCasing.FireboxCasingType casingType = BlockFireboxCasing.getState(blockState);
            Object currentCasingType = blockWorldState.getMatchContext().getOrPut("CasingType", casingType);
            if (!currentCasingType.toString().equals(casingType.toString())) {
                blockWorldState.setError(new PatternStringError("gtb.multiblock.pattern.error.fire_boxes"));
                return false;
            }
            blockWorldState.getMatchContext().getOrPut("VABlock", new LinkedList<>()).add(blockWorldState.getPos());
            return true;
        }
        return false;
    }, () -> ArrayUtils.addAll(Arrays.stream(BlockFireboxCasing.FireboxCasingType.values())
            .map(type -> new BlockInfo(MetaBlocks.BOILER_FIREBOX_CASING.getState(type), null)).toArray(BlockInfo[]::new)))
            .addTooltips("gtb.multiblock.pattern.error.fire_boxes");

    //  Tier Traceability Predicates
    public static Supplier<TierTraceabilityPredicate> GTB_MACHINE_CASINGS = () -> new TierTraceabilityPredicate(MAP_MACHINE_CASING,"MachineCasingType",null);
    public static Supplier<TierTraceabilityPredicate> GTB_CP_CASING = () -> new TierTraceabilityPredicate(MAP_CP_CASING,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_CP_CASING.get(s)).getIntTier()),"ChemicalPlantCasing",null);
    public static Supplier<TierTraceabilityPredicate> GTB_CP_TUBE = () -> new TierTraceabilityPredicate(MAP_CP_TUBE,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_CP_TUBE.get(s)).getIntTier()),"ChemicalPlantTube",null);
    public static Supplier<TierTraceabilityPredicate> GTB_PA_CASING = () -> new TierTraceabilityPredicate(MAP_PA_CASING,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_PA_CASING.get(s)).getIntTier()), "PACasing", null);
    public static Supplier<TierTraceabilityPredicate> GTB_PA_INTERNAL_CASING = () -> new TierTraceabilityPredicate(MAP_PA_INTERNAL_CASING,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_PA_INTERNAL_CASING.get(s)).getIntTier()), "PAInternalCasing", null);
    public static Supplier<TierTraceabilityPredicate> GTB_CA_TIERED_CASING = () -> new TierTraceabilityPredicate(MAP_CA_TIRED_CASING,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_CA_TIRED_CASING.get(s)).getIntTier()), "CATieredCasing", null);
    public static Supplier<TierTraceabilityPredicate> GTB_QFT_MANIPULATOR = () -> new TierTraceabilityPredicate(MAP_QFT_MANIPULATOR,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_QFT_MANIPULATOR.get(s)).getIntTier()), "QFTManipulator", null);
    public static Supplier<TierTraceabilityPredicate> GTB_QFT_SHIELDING_CORE = () -> new TierTraceabilityPredicate(MAP_QFT_SHIELDING_CORE,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_QFT_SHIELDING_CORE.get(s)).getIntTier()), "QFTShieldingCore", null);
    public static Supplier<TierTraceabilityPredicate> GTB_QFT_GLASS = () -> new TierTraceabilityPredicate(MAP_QFT_GLASS,
            Comparator.comparing((s) -> ((WrappedIntTier)MAP_QFT_GLASS.get(s)).getIntTier()), "QFTGlass", null);

    //  Optional Traceability Predicates
    public static TraceabilityPredicate optionalStates(String mark, IBlockState... allowedStates) {
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState state = blockWorldState.getBlockState();
            if (state.getBlock() instanceof VariantActiveBlock) {
                blockWorldState.getMatchContext().getOrPut("VABlock", new LinkedList<>()).add(blockWorldState.getPos());
            }
            if (ArrayUtils.contains(allowedStates, state)) {
                return (blockWorldState.getMatchContext().getOrPut(mark, true));
            }
            return blockWorldState.getMatchContext().get(mark) == null;
        }, getCandidates(allowedStates));
    }

    public static TraceabilityPredicate optionalAbilities(String mark, MultiblockAbility<?>... allowedAbilities) {
        return new TraceabilityPredicate(blockWorldState -> {
            TileEntity tileEntity = blockWorldState.getTileEntity();
            if (tileEntity instanceof IGregTechTileEntity) {
                MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
                if (metaTileEntity instanceof IMultiblockAbilityPart<?> && ArrayUtils.contains(allowedAbilities, ((IMultiblockAbilityPart<?>) metaTileEntity).getAbility())) {
                    Set<IMultiblockPart> partsFound = blockWorldState.getMatchContext().getOrCreate("MultiblockParts", HashSet::new);
                    partsFound.add((IMultiblockPart) metaTileEntity);
                    return (blockWorldState.getMatchContext().getOrPut(mark, true));
                }
            }
            return blockWorldState.getMatchContext().get(mark) == null;
        }, getCandidates(Arrays.stream(allowedAbilities).flatMap(ability -> MultiblockAbility.REGISTRY.get(ability).stream()).toArray(MetaTileEntity[]::new)));
    }

    //  Utils
    public static Supplier<BlockInfo[]> getCandidates(IBlockState... allowedStates) {
        return () -> Arrays.stream(allowedStates).map(state -> new BlockInfo(state, null)).toArray(BlockInfo[]::new);
    }

    public static Supplier<BlockInfo[]> getCandidates(MetaTileEntity... metaTileEntities) {
        return () -> Arrays.stream(metaTileEntities)
                .filter(Objects::nonNull)
                .map(tile -> new BlockInfo(MetaBlocks.MACHINE.getDefaultState(), GTBUniverUtil.getTileEntity(tile)))
                .toArray(BlockInfo[]::new);
    }
}
