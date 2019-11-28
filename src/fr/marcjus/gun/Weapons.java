package fr.marcjus.gun;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Weapons implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		
		if (e.getDamager() instanceof Snowball){
			Snowball s = (Snowball) e.getDamager();
			
			if(s.getShooter() instanceof Player){
				Player player = (Player) s.getShooter();
				ItemStack it =player.getItemInHand();
				
				if(player.getItemInHand().getType() == Material.DIAMOND_HOE && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equals("§4Pistolet")){
					e.setDamage(3.5);
				}
			}
			
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		Player player = e.getPlayer();
		Action action = e.getAction();
		ItemStack it = e.getItem();
		
		if(it != null && e.getItem().getType() == Material.DIAMOND_HOE && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equals("§4Pistolet")){
			
			if(action == Action.RIGHT_CLICK_AIR){
				player.launchProjectile(Snowball.class);
			}
			
		}else if(it != null && it.getType() == Material.SLIME_BALL && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equals("§4Grenade")){
			if(action == Action.RIGHT_CLICK_AIR){
				Item ball = player.getWorld().dropItem(player.getLocation(), Main.grenade);
				ball.setVelocity(player.getEyeLocation().getDirection());
				
				Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						
						ball.getWorld().createExplosion(ball.getLocation(), 5f);
						ball.getWorld().playEffect(ball.getLocation(), Effect.SMOKE, 10);
						ball.remove();
						
					}
				}, 100);
			}
		}
		
	}
	

}
