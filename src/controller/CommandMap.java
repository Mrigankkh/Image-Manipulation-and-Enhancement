package controller;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.ColorComponent;
import controller.commands.ColorCorrection;
import controller.commands.Command;
import controller.commands.Compress;
import controller.commands.HorizontalFlip;
import controller.commands.IntensityComponent;
import controller.commands.LevelAdjust;
import controller.commands.Load;
import controller.commands.LumaComponent;
import controller.commands.RGBSplit;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.ValueComponent;
import controller.commands.VerticalFlip;

import java.util.HashMap;

/**
 * The command map class maps a string for a command to a command that is executed.
 */
public class CommandMap {

  private HashMap<String, Command> commandMap;

  /**
   * This constructs a new command map that maps a string for a command to a command that is
   * executed.
   *
   * @param operationParameters a String array of operation parameters for the command
   */
  public CommandMap(String[] operationParameters) {
    this.commandMap = new HashMap<>();
    commandMap.put("Load", new Load(operationParameters));
    commandMap.put("Brighten", new Brighten(operationParameters));
    commandMap.put("Blur", new Blur(operationParameters));
    commandMap.put("Sepia", new Sepia(operationParameters));
    commandMap.put("Sharpen", new Sharpen(operationParameters));
    commandMap.put("Horizontal", new HorizontalFlip(operationParameters));
    commandMap.put("Vertical", new VerticalFlip(operationParameters));
    commandMap.put("Intensity", new IntensityComponent(operationParameters));
    commandMap.put("Luma", new LumaComponent(operationParameters));
    commandMap.put("Value", new ValueComponent(operationParameters));
    commandMap.put("Color Correct", new ColorCorrection(operationParameters));
    commandMap.put("R-Component", new ColorComponent(operationParameters, 0));
    commandMap.put("G-Component", new ColorComponent(operationParameters, 1));
    commandMap.put("B-Component", new ColorComponent(operationParameters, 2));
    commandMap.put("Compress", new Compress(operationParameters));
    commandMap.put("Save", new Save(operationParameters));
    commandMap.put("Level Adjust", new LevelAdjust(operationParameters));
    commandMap.put("LevelAdjust", new LevelAdjust(operationParameters));
    commandMap.put("ColorCorrect", new ColorCorrection(operationParameters));
    commandMap.put("RGB Split", new RGBSplit(operationParameters));
  }

  /**
   * Returns commandMap.
   *
   * @return the HashMap object commandMap
   */
  HashMap<String, Command> getCommandMap() {
    return this.commandMap;
  }

  /**
   * Puts a new key-value pair in the commandMap.
   *
   * @param key   the String key
   * @param value the Command value
   */
  void addCommand(String key, Command value) {
    commandMap.put(key, value);
  }

}
