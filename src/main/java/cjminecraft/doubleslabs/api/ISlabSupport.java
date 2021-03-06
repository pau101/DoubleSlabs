package cjminecraft.doubleslabs.api;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public interface ISlabSupport {
    @Deprecated
    default boolean isValid(IBlockReader world, BlockPos pos, BlockState state) {
        return false;
    };

    @Deprecated
    default boolean isValid(ItemStack stack, PlayerEntity player, Hand hand) {
        return false;
    };

    default boolean isHorizontalSlab(IBlockReader world, BlockPos pos, BlockState state) {
        return isValid(world, pos, state);
    }

    default boolean isHorizontalSlab(ItemStack stack, PlayerEntity player, Hand hand) {
        return isValid(stack, player, hand);
    }

    default boolean isVerticalSlab(IBlockReader world, BlockPos pos, BlockState state) {
        return false;
    }

    default boolean isVerticalSlab(ItemStack stack, PlayerEntity player, Hand hand) {
        return false;
    }

    default BlockState getStateForDirection(World world, BlockPos pos, ItemStack stack, BlockItemUseContext context, Direction direction) {
        BlockItem slab = (BlockItem) stack.getItem();
        return slab.getBlock().getStateForPlacement(context);
    }

    default Direction getDirection(World world, BlockPos pos, BlockState state) {
        return Direction.NORTH;
    }

    default SlabType getHalf(World world, BlockPos pos, BlockState state) {
        return SlabType.BOTTOM;
    };

    default BlockState getStateForHalf(World world, BlockPos pos, ItemStack stack, BlockItemUseContext context, SlabType half) {
        BlockItem slab = (BlockItem) stack.getItem();
        return slab.getBlock().getStateForPlacement(context);
    }

    default boolean areSame(World world, BlockPos pos, BlockState state, ItemStack stack) {
        return ((BlockItem) stack.getItem()).getBlock() == state.getBlock();
    }

    default float getOffsetY(boolean positive) {
        return 0;
    }

    default ActionResultType onActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        return state.onBlockActivated(world, player, hand, hit);
    }
}
