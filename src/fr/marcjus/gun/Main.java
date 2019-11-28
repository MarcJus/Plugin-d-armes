package fr.marcjus.gun;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	private static Main instance;
	
	public static Main getInstance(){
		return instance;
	}
	public static ItemStack gun = new ItemStack(Material.DIAMOND_HOE, 1);
	public static ItemStack grenade = new ItemStack(Material.SLIME_BALL, 1);
	public static ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
	
	@Override
	public void onEnable() {
		gun.setItemMeta(metaGun());
		grenade.setItemMeta(metaGrenade());
		sword.setItemMeta(metaSword());
		System.out.println("Gun actif");
		getServer().getPluginManager().registerEvents(new Weapons(), this);
		char[] keys1 = {'h'};
		char[] keys2 = {'s'};
		char[] keys3 = {'s'};
		Material[] materials1 = {Material.DIAMOND_HOE};
		Material[] materials2 = {Material.SLIME_BALL};
		Material[] materials3 = {Material.DIAMOND_SWORD};
		String[] shape1 = {"   ", " h ", "   "};
		String[] shape2 = {"   ", " s ", "   "};
		String[] shape3 = {"   ", " s ", "   "};
		getServer().addRecipe(addCraft(gun, 1, keys1, materials1, shape1));
		getServer().addRecipe(addCraft(grenade, 1, keys2, materials2, shape2));
		getServer().addRecipe(addCraft(sword, 1, keys3, materials3, shape3));
	}
	
	@Override
	public void onDisable() {
		System.out.println("Gun inactif");
	}
	
	@SuppressWarnings("deprecation")
	public ShapedRecipe addCraft(ItemStack result, int ingredients, char[] key, Material[] ingredient, String[] shape ){
		ShapedRecipe recipe = new ShapedRecipe(result);
		recipe.shape(shape);
		for(int i = 0; i<ingredients; i++){
			recipe.setIngredient(key[i], ingredient[i]);
		}
		
		return recipe;
		
	}
	
	public ItemMeta metaGun(){
		ItemMeta meta = gun.getItemMeta();
		meta.setDisplayName("§4Pistolet");
		meta.setLore(Arrays.asList("Appuyez pour tirer", "Ne marche pas sur en cliquant sur les blocs", "Tapez l'entité pour la faire reculer loin(très)"));
		meta.addEnchant(Enchantment.KNOCKBACK, 5, true);
		return meta;
	}
	
	public ItemMeta metaGrenade(){
		ItemMeta meta = grenade.getItemMeta();
		meta.setDisplayName("§4Grenade");
		meta.setLore(Arrays.asList("Appuyez pour lancer une grenade", "Elle explosera au bout de 5 secondes"));
		return meta;
	}
	
	public ItemMeta metaSword(){
		ItemMeta meta = sword.getItemMeta();
		meta.addEnchant(Enchantment.DAMAGE_ALL, 9999, true);
		meta.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
		meta.setDisplayName("§cÉppée du dieu");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
		meta.setUnbreakable(true);
		meta.setLore(Arrays.asList("§eL'éppée du dieu tue tous les monstres et les joueurs en un seu coup !", "§eMême l'ender dragon et le wither!", "§cCelui qui a cette épée signifie qu'il en est digne!"));
		return meta;
	}
}
