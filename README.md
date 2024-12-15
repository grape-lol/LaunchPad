A Minecraft plugin designed to create launch pads that launches players into the air when they step on a specific block combination which you can change in the config!

Example Launch Pad:
![launch pad example](https://cdn.modrinth.com/data/TuKBwiKu/images/5ed4c2e4615a5d864b320cdd1822099421e4c631.png)

**config.yml**

```
enable-plugin: true
disable-fall-damage: true

# You need to have the name exact you can find a list here: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html
top-block: "STONE_PRESSURE_PLATE"
under-block: "GOLD_BLOCK"

# I recommend testing these values for your needs
y-velocity: 2 # How high you want the player to go (not the exact y level because of multiply-velocity)
multiply-velocity: 3 # How much the players momentum multiples by

# This sends a message when the player gets launched
message: true
message-cooldown: 1500 # In milliseconds 1000 = 1sec, this is recommended so it doesn't spam there chat
launch-message: "&aWeeee!"
```
