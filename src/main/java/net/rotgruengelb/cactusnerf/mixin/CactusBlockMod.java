package net.rotgruengelb.cactusnerf.mixin;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(CactusBlock.class)
public class CactusBlockMod {

    @WrapOperation(
            method = "randomTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;isAir(Lnet/minecraft/util/math/BlockPos;)Z")
    )
    private boolean requireAnyAdjacentBlockToBeAir(ServerWorld world, BlockPos pos, Operation<Boolean> original) {
        return (original.call(world, pos) && world.isAir(pos.north()) && world.isAir(pos.east()) && world.isAir(pos.south()) && world.isAir(pos.west()));
    }
}
