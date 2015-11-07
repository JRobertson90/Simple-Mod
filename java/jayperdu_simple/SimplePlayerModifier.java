package jayperdu_simple;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import com.google.common.collect.Multimaps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.UUID;

import static jayperdu_simple.SimpleModSettings.*;

public class SimplePlayerModifier {

	private static final UUID MOVEMENT_SPEED_UID = UUID.fromString("206a89dc-ae78-4c4d-b42c-3b31db3f5a7c");

	public static void setup() {
		MinecraftForge.EVENT_BUS.register(new SimplePlayerModifier());
		FMLCommonHandler.instance().bus().register(new SimplePlayerModifier());
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(event.phase == TickEvent.Phase.END) {

			EntityPlayer player = event.player;

			AttributeModifier modifier = new AttributeModifier(MOVEMENT_SPEED_UID, "Movement Speed Boost", MOVEMENT_SPEED_BOOST, 0);
			Multimap modifiersToAdd = Multimaps.forMap(ImmutableMap.of("generic.movementSpeed", modifier));
			player.getAttributeMap().applyAttributeModifiers(modifiersToAdd);

			if (player.worldObj.isRemote) {
				if (player.isInWater() || player.isInLava()) {
					handleHorizontalSwim(player);
					handleVerticalSwim(player);
				}
			}
		}
	}

	private void handleHorizontalSwim(EntityPlayer player) {
		double yawHead = player.getRotationYawHead();
		while (yawHead >= 0.0) {
			yawHead -= 360.0;
		}
		while (yawHead <= 0.0) {
			yawHead += 360.0;
		}

		GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
		KeyBinding left = gameSettings.keyBindLeft;
		KeyBinding forward = gameSettings.keyBindForward;
		KeyBinding right = gameSettings.keyBindRight;
		KeyBinding back = gameSettings.keyBindBack;

		if(left.isKeyDown()) {
			double angle = Math.toRadians(yawHead + 0.0);
			player.motionX += (Math.cos(angle) * SWIM_SPEED);
			player.motionZ += (Math.sin(angle) * SWIM_SPEED);
		}
		if(forward.isKeyDown()) {
			double angle = Math.toRadians(yawHead + 90.0);
			player.motionX += (Math.cos(angle) * SWIM_SPEED);
			player.motionZ += (Math.sin(angle) * SWIM_SPEED);
		}
		if(right.isKeyDown()) {
			double angle = Math.toRadians(yawHead + 180.0);
			player.motionX += (Math.cos(angle) * SWIM_SPEED);
			player.motionZ += (Math.sin(angle) * SWIM_SPEED);
		}
		if(back.isKeyDown()) {
			double angle = Math.toRadians(yawHead + 270.0);
			player.motionX += (Math.cos(angle) * SWIM_SPEED);
			player.motionZ += (Math.sin(angle) * SWIM_SPEED);
		}
	}

	private void handleVerticalSwim(EntityPlayer player) {

		GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
		KeyBinding jump = gameSettings.keyBindJump;
		KeyBinding sneak = gameSettings.keyBindSneak;

		if(jump.isKeyDown()) {
			player.motionY += isAirAbovePlayer(player) ? SWIM_JUMP_OUT_BOOST : SWIM_SPEED;
			if(player.isInLava()) {
				player.motionY += 0.7;
			}
		} else if(sneak.isKeyDown()) {
			player.motionY -= SWIM_SPEED;
		} else {
			// Drift to a stop if player is moving downwards
			if(player.motionY < 0) {

				// Set speed to nothing when close
				final double RISING_FORCE = 0.02;
				if(player.motionY > -RISING_FORCE) {
					player.motionY = 0;
				} else {
					player.motionY += RISING_FORCE;
				}
			}
			// Drift to a stop if player is moving upwards
			else if(player.motionY > 0) {
				// Set speed to nothing when close
				if(player.motionY < 0.005) {
					player.motionY = 0;
				}
				else if(player.motionY < 0.02) {
					player.motionY += 0.01;
				} else {
					// Even though adding - net result is down because of gravity.
					player.motionY += 0.02;
				}
			}
		}
	}

	private boolean isAirAbovePlayer(EntityPlayer player) {
		return player.worldObj.isAirBlock(new BlockPos(player.posX, player.posY + 1, player.posZ));
	}

	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onLivingJumpEvent(LivingJumpEvent event) {
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			superJump(player);
		}
	}

	public static void superJump(EntityPlayer player) {
		player.motionY += JUMP_HEIGHT_INCREASE;
		double horizontalMultiplier = player.isSprinting() ? JUMP_HORIZONTAL_MULTIPLIER : JUMP_HORIZONTAL_SPRINT_MULTIPLIER;
		player.motionX *= horizontalMultiplier;
		player.motionZ *= horizontalMultiplier;
	}

	@SuppressWarnings("unused")
	@SubscribeEvent
	public void livingFall(LivingFallEvent event) {
	    if (event.entityLiving instanceof EntityPlayer) {
			event.distance -= FALL_DAMAGE_HEIGHT_REDUCE;
	    }
	}

}
