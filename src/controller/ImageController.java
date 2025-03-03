package controller;

import controller.commands.ColorCorrection;
import controller.commands.Command;
import controller.commands.Compress;
import controller.commands.Histogram;
import controller.commands.LevelAdjust;
import controller.commands.Load;
import controller.commands.Sharpen;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.HorizontalFlip;
import controller.commands.RGBSplit;
import controller.commands.RGBCombine;
import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.ValueComponent;
import controller.commands.VerticalFlip;
import controller.commands.IntensityComponent;
import controller.commands.LumaComponent;
import controller.commands.ColorComponent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import model.ImageModel;
import view.ImageView;

/**
 * The Controller class in the MVC design pattern is responsible for handling user input, invoking
 * corresponding methods in the Model, and updating the View accordingly with an appropriate message
 * on successful completion and in exception cases.
 */
public class ImageController extends AbstractImageController {

  private final Readable in;
  private final ImageView view;
  private final ImageModel model;
  private final String[] args;


  /**
   * Creates a controller.ImageController object.
   *
   * @param in    is the inputStream
   * @param out   is the outStream
   * @param view  Is the View
   * @param model is the Model.
   * @param args  is the arguments passed to this program.
   */
  public ImageController(Readable in, Appendable out, ImageView view, ImageModel model,
      String[] args) {
    this.in = in;
    this.model = model;
    this.view = view;
    this.args = args;
  }

  /**
   * Run the controller. First run the file if any provided in the argument to the program. Quit on
   * q.
   */
  public void run() {

    // run the file if it exists.
    if (args.length > 0 && args[0].equals("-file")) {
      runFile("./" + args[1]);
      return;
    } else {
      view.printMessage("No valid file path provided in arguments or "
          + "file path provided is invalid.");
    }

    // take inputs from the CLI and execute them. Quit on input q.
    Scanner scan = new Scanner(this.in);
    while (scan.hasNextLine()) {
      view.printMessage("Enter a command: ");
      String currentLine = scan.nextLine();
      if (currentLine.equals("q")) {
        view.printMessage("The program has been terminated.");
        return;
      }
      try {
        execute(currentLine);
      } catch (FileNotFoundException e) {
        view.printMessage(e.getMessage());
      }


    }
  }

  private void runFile(String filePath) {
    ArrayList<String> commands = new ArrayList<>();
    try {
      commands = getCommandsFromFile(filePath);
    } catch (Exception e) {
      view.printMessage(e.toString());
    }
    for (String command : commands) {
      try {
        execute(command);
      } catch (Exception e) {
        view.printMessage(e.getMessage());
      }
    }
  }

  private ArrayList<String> getCommandsFromFile(String filePath) {
    Readable fileReader;
    ArrayList<String> commands = new ArrayList<>();
    try {
      fileReader = new FileReader(filePath);
      Scanner fileScanner = new Scanner(fileReader);
      while (fileScanner.hasNextLine()) {
        String currentLine = fileScanner.nextLine();
        if (currentLine.contains("#")) {
          currentLine = currentLine.substring(0, currentLine.indexOf("#"));
        }
        commands.add(currentLine);
      }

    } catch (FileNotFoundException f) {
      view.printMessage("The file at path " + filePath + " cannot be found.");
    }

    return commands;
  }

  private void execute(String command)
      throws IllegalArgumentException, FileNotFoundException {
    if (command.isEmpty()) {
      return;
    }
    String[] parsedCommand = command.stripLeading().split("\\s+");
    String operationName = parsedCommand[0];
    String[] operationParameters = new String[parsedCommand.length - 1];
    System.arraycopy(parsedCommand, 1, operationParameters, 0, operationParameters.length);
    Command cmd = null;
    try {
      switch (operationName) {
        case "level-adjust": {
          cmd = new LevelAdjust(operationParameters);
          break;
        }
        case "color-correct": {
          cmd = new ColorCorrection(operationParameters);
          break;
        }
        case "histogram": {
          cmd = new Histogram(operationParameters);
          break;
        }
        case "compress": {
          cmd = new Compress(operationParameters);
          break;
        }
        case "load": {
          cmd = new Load(operationParameters);
          break;
        }
        case "run": {
          executeRunFile(operationParameters);
          break;
        }
        case "sepia": {
          cmd = new Sepia(operationParameters);
          break;
        }
        case "red-component": {
          cmd = new ColorComponent(operationParameters, 0);
          break;
        }
        case "green-component": {
          cmd = new ColorComponent(operationParameters, 1);
          break;
        }
        case "blue-component": {
          cmd = new ColorComponent(operationParameters, 2);
          break;
        }
        case "value-component": {
          cmd = new ValueComponent(operationParameters);
          break;
        }
        case "intensity-component": {
          cmd = new IntensityComponent(operationParameters);
          break;
        }
        case "luma-component": {
          cmd = new LumaComponent(operationParameters);
          break;
        }

        case "vertical-flip": {
          cmd = new VerticalFlip(operationParameters);
          break;
        }
        case "horizontal-flip": {
          cmd = new HorizontalFlip(operationParameters);
          break;
        }
        case "rgb-split": {
          cmd = new RGBSplit(operationParameters);
          break;
        }
        case "rgb-combine": {
          cmd = new RGBCombine(operationParameters);
          break;
        }
        case "blur": {
          cmd = new Blur(operationParameters);
          break;
        }
        case "brighten": {
          cmd = new Brighten(operationParameters);
          break;
        }
        case "sharpen": {
          cmd = new Sharpen(operationParameters);
          break;
        }
        case "save": {
          cmd = new Save(operationParameters);
          break;
        }
        default: {
          try {
            view.printMessage("Please enter a valid operation to execute.");
          } catch (IllegalArgumentException exception) {
            view.printMessage(exception.getMessage());
          }
          return;

        }
      }

      if (cmd != null) {
        cmd.execute(model);
      }
      view.printMessage("Operation " + operationName + " complete.");
    } catch (Exception e) {
      view.printMessage(e.getMessage());
    }

  }


  private void executeRunFile(String[] operationParameters) {
    if (operationParameters.length != 1) {
      throw new IllegalArgumentException(
          "Run expected 1 argument; found " + operationParameters.length);
    }
    runFile(operationParameters[0]);

  }


}
