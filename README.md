# GRIME: Graphical Image Manipulation and Enhancement

![Static Badge](https://img.shields.io/badge/Java-orange)
![Static Badge](https://img.shields.io/badge/JUnit-green)
![Static Badge](https://img.shields.io/badge/Object%20Oriented%20Programming-blue)


This project is an Image Manipulation and Enhancement tool developed as a part of coursework for the course Programming Design and Paradigms. 

<h1>Introduction</h1>
This is a java tool built using MVC architecture to perform some basic operations on images. It supports text-based user interface where in a user can enter commands as well as a Graphical User Interface. Various image manipulation operations such as Sepia, Blur, Sharpen, Level Adjust , Color COrrect, Luma , Value etc are supported.

The focus of this project was to develop a tool while adhering to an elite coding design, following the SOLID principles and maintaining good readibility..
<h1>Access</h1>
This project was a part of coursework for the graduate course Programming Design and Paradigms and using the source code for your PDP Assignments would be a violation of academic integrity. 
<h1>How To Run The Application</h1>
Download the JAR file.
<h2>Use the GUI</h2>
Double click on the JAR file or use command "java -jar pdp-assignment5.jar" in terminal
Download the code and run the main method. 
<h2>Use a script</h2>
Use command "java -jar pdp-assignment5.jar -file path-of-script-file" in terminal
<h2>Use the text bases Comand Line interface</h2>
Use command "java -jar pdp-assignment5.jar -text" in terminal and enter commands in the Command line.

<h1>Class Diagram</h1>
<img width="808" alt="Diagram" src="https://github.com/user-attachments/assets/f9fb1a70-5209-489f-b762-0f57d2b9709a" />

<h1>Image used</h1>
  <img src="res/Road.jpg"  title="Test Image">
Image source: This image is owned by Mrigank Khandelwal (author). Use of this image in this project is authorized.

<h1>Design Changes</h1>
<h2>New functionality</h2>
The new functionalities introduced in this assignment were:
<ul>
<li>Compression</li>
<li>Histogram</li>
<li>Level Adjustment</li>
<li>Color Correction</li>
</ul>
For these functionalities, in the controller, new command classes were created for each functionality that extend AbstractCommand class. In the controller, we add these commands in the switch statement.
This ensures no change in other command classes and minimal change in the ImageController (just populating the switch statement).

In the ImageModel interface, 4 new methods for each of these commands are added along with their implementations in Gallery.


<h2>Split Functionality</h2>
We assume that if no split percent is given, the split percent is 0 which is equivalent to no split. In the model side, all operations that require a split percent were modified to take as an additional parameter. In all the operation loops, a width variable was introduced and used where width  = this.width()*splitpercent/100.
In the controller side, the numCommands variable which was initially a list, was made an array of the number of acceptable arguments. So if this array has {2,4}, it means the command can accept 2 or 4 arguments. Based on this, if commands of operations that accept this split argument have "split" as the corresponding argument and a valid numerical split percent as the subsequent argument, then the model is called with that split percent. If not split percent is passed, the model is called with a split percent of 0.

<h3>Justification</h3>
The change on the model side was necessary since we need to give the model methods extra data. Since there was minimal logical change, we added a splitPercent parameter to the relevant methods and changed the loop range to accomodate for this.

On the controller side, the changes were more significant. We used an array for numParameters to make the tool more extensible (so that if more such arguments are added, minimal change is required, we only need to change the numParameters and add the relevant number of parameters).
All command classes have an argument error method that can check for command specific argument errors. The commands accepting splitPercents can check relevant argument errors through this method.

<h2>Running a script file using command line arguments such as -file filename.txt</h2>
Since the previous verion of the assignment already passed the String[] args to the Controller object from the main, we used this and called the same method on the filename that was being called in the "run scriptfile.txt" command.


<h1>Design Changes for Graphical User Interface</h1>
<h2>View</h2>
A new Graphical User Interface View is added. This view has 1 image panel that shows the image currently being operated on, a panel for the histogram of the image currently being operated on, a Navigation Bar with operations that the GRIME supports, a preview Panel for operations that support preview and input and error dialog boxes.
The ImageViewGUI is the parent JFrame of the GUI and it is comprised of 4 components:
<h3>NavigationBar</h3>
This has menuItems for all the operations that the tool supports. File (Load, Compress, Save) , Filters(Blur, brighten, sharpen, color correct, sepia) , Flip(Horizontal,Vertical) and Visualize(Luma, Value, Intensity, RGB Components).
Each menu item has a menuItemActionListener attached to it whose sole purpose is to call the menuItemClicked feature and pass the control to the Controller. The only scope of these menu items is to be clicked, once clicked, the controller handles the next steps.
<h3>ImagePanel</h3>
This is initially empty and once the user loads an image successfully, this image is displayed here. It uses horizontal and vertical scrolls if the image is larger than the frame. Its only job is to display the image the controller sends it.
<h3>HistogramPanel</h3>
Similar to imagePanel, its job is to display the histogram image that the controller sends it.
<h3>PreviewDialog</h3>
This Dialog pops when the user clicks a menu item that supports preview. It has a split slider for preview, a panel for the preview image and 2 buttons, one for cancel and one for confirm.
<h3>ImageFileChooser</h3>
Choose the image file for loading or saving imageFile
<h3>InputDialog</h3>
This is a dialogBox popup for taking inputs for operations that require inputs such as level Adjust, brighten and compress.</h3>

<h2>Design Choices for View</h2>
A new interface ImageViewG was created that extends the original ImageView interface. 
<br/>
<b>Justification: </b> There is very little (no) common code between the ImageViewCLI and ImageViewGUI and it made more sense to create a new Interface and extend it rather than use ethe same one. This way, other GUIs can also use this Interface.

<h2>Controller</h2>
A new Controller Class, ImageControllerGUI was created that extends the abstractImageController class. It also implements a Features Interface that represents the features that the model supports.
An abstarctImageController class was created to abstract out the common methods between the GUIController and CLIContoller.


<h2>Design Changes</h2>
A new controller was added. This controller listens to each component of the View using the features. There is a menuItemClickHandler method, previewHanlder methods that handle their respective components using features. 
<b>Justification: </b> New controller was required because the interaction between the model and the veiew in the GUI is fundamentally different. Moreover, the choice to listen to each component made sure that each listener for a single view component was responsible for a single atomic task. For ex: each menu item handler only does the processing required then the menuItem is clicked. If the operation is a flip, it will flip the image otherwise if its a visualize operation, it will open the preview screen and relinquish control.

<h2>Model</h2>
The ImageModel interface remains the same since other Views (CommandLine) already uses it. Minor changes in the implementation of a few methods have been made for optimization. The model keeps a record of old image as well as new image. There is no change in how it is exposed or used.
<h1>Supported Operations in Graphical View and How To Use Them</h1>
<h2>Load</h2>
<ul>
<li>Select File at the top left corner of the screen.</li>
<li>Choose Load from the menu.</li>
<li>Pick a file to load from the file chooser.</li>
<li>The GUI will show the image.</li>  
</ul>
<h2>Save</h2>
<ul>
<li>Select File at the top left corner of the screen.</li>
<li>Choose Save from the menu.</li>
<li>Pick a place in the file chooser to save the image, and type in the name and extension for the image. Valid extensions include .jpg, .png, and .ppm.</li>  
</ul>
<h2>Compress</h2>
<ul>
<li>Select File at the top left corner of the screen.</li>
<li>Choose Compress from the menu.</li>
<li>A pop up will appear. Enter a number from 0-100.</li>
<li>An error pop up will appear if the user does not enter a number from 0-100.</li>
<li>The image along with its histogram will show up in the GUI.</li>           
</ul>
<h2>Visualize Red Component</h2>
<ul>
<li>Select Visualize from the navigation bar.</li>
<li>Choose R-Component from the menu.</li>
<li>The image along with its histogram will show up in the GUI.</li>             
</ul>
<h2>Visualize Green Component</h2>
<ul>
<li>Select Visualize from the navigation bar.</li>
<li>Choose G-Component from the menu.</li>
<li>The image along with its histogram will show up in the GUI.</li>               
</ul>
<h2>Visualize Blue Component</h2>
<ul>
<li>Select Visualize from the navigation bar.</li>
<li>Choose B-Component from the menu.</li>
<li>The image along with its histogram will show up in the GUI.</li>                 
</ul>
<h2>Visualize Intensity</h2>
<ul>
<li>Select Visualize from the navigation bar.</li>
<li>Choose Intensity from the menu.</li>
<li>A percentage of the image as toggled in the split slider will be the intensity of the image.</li>
<li>Press the cancel button to cancel the operation or press the confirm button to apply the intensity to the whole image.</li>
<li>The image along with its histogram will show up in the GUI.</li>                   
</ul>
<h2>Visualize Luma</h2>
<ul>
<li>Select Visualize from the navigation bar.</li>
<li>Choose Luma from the menu.</li>
<li>A percentage of the image as toggled in the split slider will be the luma of the image.</li>
<li>Press the cancel button to cancel the operation or press the confirm button to apply the luma to the whole image.</li>
<li>The image along with its histogram will show up in the GUI.</li>   
</ul>
<h2>Visualize Value</h2>
<ul>
<li>Select Visualize from the navigation bar.</li>
<li>Choose Value from the menu.</li>
<li>A percentage of the image as toggled in the split slider will be the value of the image.</li>
<li>Press the cancel button to cancel the operation or press the confirm button to apply the value to the whole image.</li>
<li>The image along with its histogram will show up in the GUI.</li>   
</ul>
<h2>Flip Horizontal</h2>
<ul>
<li>Select Flip from the navigation bar.</li>
<li>Choose Horizontal from the menu.</li>
<li>The image along with its histogram will show up in the GUI.</li>                   
</ul>
<h2>Flip Vertical</h2>
<ul>
<li>Select Flip from the navigation bar.</li>
<li>Choose Vertical from the menu.</li>
<li>The image along with its histogram will show up in the GUI.</li>                   
</ul>
<h2>Blur</h2>
<ul>
<li>Select Filters from the navigation bar.</li>
<li>Choose Blur from the menu.</li>
<li>A percentage of the image as toggled in the split slider will be blurred.</li>
<li>Press the cancel button to cancel the operation or press the confirm button to apply the blur to the whole image.</li>
<li>The image along with its histogram will show up in the GUI.</li>     
</ul>
<h2>Sharpen</h2>
<ul>
<li>Select Filters from the navigation bar.</li>
<li>Choose Sharpen from the menu.</li>
<li>A percentage of the image as toggled in the split slider will be sharpened.</li>
<li>Press the cancel button to cancel the operation or press the confirm button to apply the sharpen to the whole image.</li>
<li>The image along with its histogram will show up in the GUI.</li>    
</ul>
<h2>Brighten/Darken</h2>
<ul>
<li>Select Filters from the navigation bar.</li>
<li>Choose Brighten from the menu.</li>
<li>A pop up will appear. Enter a positive integer to brighten the image and a negative integer to darken the image.</li>  
<li>An error pop up will appear if the user does not enter an integer.</li>
<li>The image along with its histogram will show up in the GUI if valid value is entered.</li>      
</ul>
<h2>Sepia</h2>
<ul>
<li>Select Filters from the navigation bar.</li>
<li>Choose Sepia from the menu.</li>
<li>A percentage of the image as toggled in the split slider will have the sepia operation performed on it.</li> 
<li>Press the cancel button to cancel the operation or press the confirm button to apply sepia to the whole image.</li>
<li>The image along with its histogram will show up in the GUI.</li>  
</ul>
<h2>Color Correct</h2>
<ul>
<li>Select Filters from the navigation bar.</li>
<li>Choose Color Correct from the menu.</li>
<li>A percentage of the image as toggled in the split slider will have the color correct operation performed on it.</li> 
<li>Press the cancel button to cancel the operation or press the confirm button to apply color correct to the whole image.</li>
<li>The image along with its histogram will show up in the GUI.</li>  
</ul>
<h2>Level Adjust</h2>
<ul>
<li>Select Level Adjust from the navigation bar.</li>
<li>Choose Level Adjust from the menu.</li>
<li>A pop up will appear with places for the user to enter black, white, and mid values.
<li>An error pop up will appear if the user does not enter a integer or if other levels adjust conditions for the values are not met.</li>  
<li>A percentage of the image as toggled in the split slider will have the levels adjustment operation performed on it if there is no error.</li>
<li>Press the cancel button to cancel the operation or press the confirm button to apply level adjust to the whole image.</li>
<li>The image along with its histogram will show up in the GUI.</li>  
</ul>  
</ul>
<h2>Split</h2>
<ul>
<li>A slider controls the split for the percentage of the image that has been operated on and the original image prior to operation.</li>
<li>The location of this slider is in a new window once the user chooses an operation that can be previewed.</li> 
<li>Toggling of the slider allows the modified image to be shown quickly in the preview.</li> 
<li>The user can cancel the operation using the cancel button or confirm the operation using the confirm button.</li>
<li>Once the operation is confirmed, the preview will close, and the operation will be applied to the whole image.</li>  
</ul>  

<h1>Supported Commands in the CLI and how to use them</h1>
<h2>load image-path image-name</h2>
<h3>Conditions:</h3>
<ul>
<li>Path name must be a valid path where that image exists. Only ppm,png,jpg,jpeg image formats are supported.</li>
<li>Image name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Example:</h3>
<ul>
<li>load Road.jpg road</li>  
</ul>  
<h2>save image-path image-name</h2>
<h3>Conditions:</h3>
<ul>
<li>Path name must be a valid path where that image can be saved. </li>
<li>Image name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>Only ppm,png,jpg,jpeg image formats are supported. If no extension is provided, default is saved as jpg.</li>
</ul>
<h3>Example:</h3>
<ul>
<li>save ./ road-horizontal.jpg</li>  
</ul>   
<h2>red-component image-name dest-image-name</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Example:</h3>
<ul>
<li>red-component road road-red</li>  
</ul>  
<h2>green-component image-name dest-image-name</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Example:</h3>
<ul>
<li>green-component road road-green</li>  
</ul>  
<h2>blue-component image-name dest-image-name</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Example:</h3>
<ul>
<li>blue-component road road-blue</li>  
</ul>  
<h2>blur image-name dest-image-name split split-percent</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>split split-percent is optional; if not provided, it is 0 by default.</li>
<li>Split percent if provided must be a double between 0 - 100</li>
</ul>
<h3>Examples:</h3>
<ul>
<li>blur road road-blur</li>
<li>blur road road-blur-50 split 50</li>  
</ul>  
<h2>sharpen image-name dest-image-name split split-percent</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>split split-percent is optional; if not provided, it is 0 by default.</li>
<li>Split percent if provided must be a double between 0 - 100</li>
</ul>
<h3>Examples:</h3>
<ul>
<li>sharpen road road-sharpen</li>
<li>sharpen road road-sharpen-70 split 70</li>  
</ul>  
<h2>sepia image-name dest-image-name split split-percent</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>split split-percent is optional; if not provided, it is 0 by default.</li>
<li>Split percent if provided must be a double between 0 - 100</li>
</ul>
<h3>Examples:</h3>
<ul>
<li>sepia road road-sepia</li>
<li>sepia road road-sepia-30 split 30</li>  
</ul>   
<h2>horizontal-flip image-name dest-image-name </h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Example:</h3>
<ul>
<li>horizontal-flip road road-horizontal</li>
</ul>  
<h2>vertical-flip image-name dest-image-name </h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Example:</h3>
<ul>
<li>vertical-flip road road-vertical</li>
</ul>  
<h2>brighten increment image-name dest-image-name </h2>
<h3>Conditions:</h3>
<ul>
<li>Increment must be an image</li>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Examples:</h3>
<ul>
<li>brighten road road-bright 50</li>
<li>brighten road road-dark -50</li>  
</ul>   
<h2>rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue </h2>
<h3>Conditions:</h3>
<ul>
<li>Increment must be an image</li>
<li>Image-name must be a valid name of image that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name,dest-image-red,dest-image-green,dest-image-blue name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Example:</h3>
<ul>
<li>rgb-split roadsp r g b</li> 
</ul>  
<h2>rgb-combine image-name dest-image-name-red dest-image-name-green dest-image-name-blue </h2>
<h3>Conditions:</h3>
<ul>
<li>Increment must be an image</li>
<li>Image-name must be a valid name of image that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>red-image, blue-image, green-image must be a valid name of image that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Example:</h3>
<ul>
<li>rgb-combine road-combine roadR roadG roadB</li> 
</ul>
<h2>luma-component image-name dest-image-name split split-percent</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>split split-percent is optional; if not provided, it is 0 by default.</li>
<li>Split percent if provided must be a double between 0 - 100</li>
</ul>
<h3>Examples:</h3>
<ul>
<li>luma-component road road-luma</li>
<li>luma-component road road-luma-30 split 30</li>  
</ul>  
<h2>value-component image-name dest-image-name split split-percent</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>split split-percent is optional; if not provided, it is 0 by default.</li>
<li>Split percent if provided must be a double between 0 - 100</li>
</ul>
<h3>Examples:</h3>
<ul>
<li>value-component road road-value</li>
<li>value-component road road-value-40 split 40</li>  
</ul>  
<h2>intensity-component image-name dest-image-name split split-percent</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>split split-percent is optional; if not provided, it is 0 by default.</li>
<li>Split percent if provided must be a double between 0 - 100</li>
</ul>
<h3>Examples:</h3>
<ul>
<li>intensity-component road road-intensity</li>
<li>intensity-component road road-intensity-40 split 40</li>  
</ul>
<h2> run script-file-path </h2>
<h3>Conditions:</h3>
<ul>
<li>Script-file-path must be a valid path to a file containing commands.</li>
</ul>
<h3>Example:</h3>
<ul>
<li>run scriptfile.txt</li>
</ul>
  
<h2> compress percentage image-name dest-image-name</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>Compress percent must be in valid range 0 - 100</li>
</ul>
<h3>Example:</h3>
<ul>
<li>compress 70 road road-compressed</li>
</ul>

<h2>color-correct image-name dest-image-name split split-percent</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>split split-percent is optional; if not provided, it is 0 by default.</li>
<li>Split percent if provided must be a double between 0 - 100</li>
</ul>
<h3>Examples:</h3>
<ul>
<li>color-correct road road-color-correct</li>
<li>color-correct road road-color-correct-50 split 50</li>  
</ul>

<h2>histogram image-name dest-image-name</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
</ul>
<h3>Example:</h3>
<ul>
<li>histogram road road-histogram</li>
</ul>  


<h2>levels-adjust b m w image-name dest-image-name split split-percent</h2>
<h3>Conditions:</h3>
<ul>
<li>Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).</li>
<li>split split-percent is optional; if not provided, it is 0 by default.</li>
<li>Split percent if provided must be a double between 0 - 100</li>
<li>0< b < m < w < 255</li>
</ul>
<h3>Examples:</h3>
<ul>
<li>level-adjust 100 150 200 road road-level-adjust</li>
<li>level-adjust 100 150 200 road road-level-adjust-50 split 50</li>  
</ul>  



<h1>Project Structure</h1>
The project uses the MVC architecture and the project has been divided into seperate model, view and controller components.

<h2>Model</h2>
This is a representation of a Gallery of Images.
<h3>Interfaces</h3>
<h4>ImageModel</h4>
 This is the Model in the MVC architecture. A representation of a collection of images and operations on the image; each with a unique image name. It supports:
 <ul>
   <li>Adding a new Image in this Gallery.</li>
   <li>Performing an Image Manipulation Operation on an image in this Gallery.</li>
   <li>Getting an image from the Gallery based on the image name.</li>
 </ul>

<h4>Image</h4>
This is a representation of an Image in the Gallery. It supports various operations on images such as Brighten, Blur, Flips, Sharpen, Blur, RGB Split, RGB Combine, Sepia, visualizing various components (Intensity, Value, Luma, R, G, B).

<h3>Classes</h3>
<h4>Matrix</h4>
This class represents a 2D matrix. The reason for using such a class in the model is because images are representations of N 2D matrices where N is the number of channels. In RGB images, N = 3. Most image manipulation operations can be broken down into basic matrix operations such as setting a value, getting a value, convolutions and multiplications.
<h4>Gallery</h4>
 A representation of a collection of images; each with a unique image name.Based on the method called from the controller, it finds the corresponding image in the gallery and performs the operation.
 <h4>AbstractImage</h4>
 An abstraction on the operations of Image. This is to account for other types of images that may be a part of the future scope of the project and may have different nuber of channels and/or different implementations of the same operations.
 <h4>RGBImage</h4>
 This class represents an RGB Image using an Array of Matrix object.It has an Image name that uniquely identifies it and an Array of Matrix object. 
<h2>View</h2>
There are 2 views, a command line view and a Graphical User interface view currently that the project supports.
<h2>Graphical User Interface</h2>
<h3>Interfaces</h3>
<h4>ImageViewG</h4>
Interface for the GUI View.
<h3>Classes</h3>
HistogramPanel: The Jpanel that display histogram
ImageFileChooser: Displays the imageFileChooser which lets user select files to load/save.
ImagePanel: The Jpanel that display image currently being worked on
ImageViewGUI: It is the main JFrame responsible for displaying the GUI.
NavigationBar: JMenuBar that is repsonsible for displaying the menuBar
PreviewDialog: It is the preview screen
PreviewSlider: It is the JSlider to adjust the preview percentage.
SplitView: It is the imagepanel for the preview.
InputDialog: Input dialogs to take in input for various operations
<h2>Command Line Interface</h2>
<h3>Interfaces</h3>
<h4>ImageView</h4>
It is responsible for printing message.These messages include error messages and status messages.
<h3>Classes</h3>
<h4>ImageViewCLI</h4>
It is responsible for printing message on the command line interface. These messages include error messages and status messages.
<h2>Controller</h2>
<h3>Interfaces</h3>
<h4>Command</h4>
It represents commands that the view may give to get relevant output form the model.
<h4>IController</h4>
This is the contorller interface.
<h3>Classes</h3>
<h4>ImageController</h4>
This is the controller responsible for taking input, delegating business logic to the Model and sending it to the View.
Its responsibilities include: 
<ul>
  <li>Taking file path provided in program argument, parsing it for commands and executing those commands.</li>
  <li> Taking commands from CLI. </li>
  <ul><li>If the command is load, it gets the image data from the filepath provided and sends a 3D int array to the model.</li>
  <li> If the command is save, it gets an int array from the model corresonding to the image name provided if exists, and saves this image data as an image file.</li>
  <li>For any other operation, it maps the operation to the method in the model and does a valid parameter check based off the arguments passed in the commands. If parameters are of the right format, it calls the relevant method in the model.</li>
  </ul>
<li>In case the model throws an exception, it relay that to the view.</li>
  <li>In case of successful operation, it sends status to view. </li>
</ul>
<h4>AbstractCommand</h4>
An abstraction for the Command Interface representing different commands that the tool accepts.
<h4>Blur</h4>
Command to perform blur on image.
<h4>Brighten</h4>
Command to perform brighten on image. A negative brighten by value will darken the image.
<h4>ColorComponent</h4>
Command to get color components of image.
<h4>Command</h4>
Representation of commands that the Image Manipulation tool supports.
<h4>Compress</h4>
Command to perform compress on an image.
<h4>HorizontalFlip</h4>
Command to perform horizontal flip on an image.
<h4>IntensityComponent</h4>
Command to perform intensity component operation.
<h4>Load</h4>
A command to perform the load operation.
<h4>LumaComponent</h4>
Command to perform luma component operation.
<h4>RGBCombine</h4>
A command to combine an RGB components into an image.
<h4>RGBSplit</h4>
A command to split an image into RGB components.
<h4>Save</h4>
Command to save an image.
<h4>Sepia</h4>
Command to perform sepia operation.
<h4>Sharpen</h4>
Command to perform sharpen operation.
<h4>ValueComponent</h4>
Command to perform value operation.
<h4>VerticalFlip</h4>
Command to perform vertical flip on an image.
<h4>Histogram</h4>
Command to create a histogram of an image.
<h4>LevelAdjust</h4>
Command to levelAdjust an image.
<h4>Compress</h4>
Command to compress an image.
<h4>ColorCorrection</h4>
Command to perform color correction on an image.




<b>The owner of all orignial images including screenshots is the author(Mrigank Khandelwal) and the use of these images in this project is authorized</b>
