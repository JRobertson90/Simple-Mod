package simple.proxy.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import com.google.common.collect.Multimaps;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import simple.SimpleModSettings;

public class ServerHandler {
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(event.phase == TickEvent.Phase.END) {
			AttributeModifier modifier = new AttributeModifier("Movement Speed Boost", SimpleModSettings.get("movement_speed_boost"), 0);
			Multimap modifiersToAdd = Multimaps.forMap(ImmutableMap.of("generic.movementSpeed", modifier));
			event.player.getAttributeMap().applyAttributeModifiers(modifiersToAdd);
		}
	}
	
	@SubscribeEvent
	public void onLivingJumpEvent(LivingJumpEvent event) {
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			superJump(player);
		}
	}
	
	public static void superJump(EntityPlayer player) {
		player.motionY += SimpleModSettings.get("jump_height_increase");
		double horizontalMultiplier = player.isSprinting() ? SimpleModSettings.get("jump_horizontal_multiplier") : SimpleModSettings.get("jump_horizontal_sprint_multiplier");
		player.motionX *= horizontalMultiplier;
		player.motionZ *= horizontalMultiplier;
	}
	

	@SubscribeEvent
	public void livingFall(LivingFallEvent event) {
	    if (event.entityLiving instanceof EntityPlayer) {
			event.distance -= SimpleModSettings.get("fall_damage_reduce");
	    }	    
	}
	
	@SubscribeEvent
	public void onAttack(LivingAttackEvent event) {
		if(event.source.isFireDamage() && event.entity instanceof EntityPlayer) {
			if(Math.random() >= SimpleModSettings.get("fire_resist_percent")) {
				event.setCanceled(true);
			}
		}
	}
}
