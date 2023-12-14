# Image-Manipulation-and-Enhancement
This project is an Image Manipulation and Enhancement tool developed as a part of coursework for the course Programming Design and Paradigms. 

<h1>Introduction</h1>
This is a java tool built using MVC architecture to perform some basic operations on images. It supports text-based user interface where in a user can enter commands as well as a Graphical User Interface. Various image manipulation operations such as Sepia, Blur, Sharpen, Level Adjust , Color COrrect, Luma , Value etc are supported.

The focus of this project was to develop a tool while adhering to an elite coding design, following the SOLID principles and maintaining good readibility..
<h1>Access</h1>
This project was a part of coursework for the graduate course Programming Design and Paradigms and sharing the source code online would be a violation of academic integrity. However, the code can be made available on demand.

<h1>How To Run The Application</h1>
Download the JAR file.
<h2>Use the GUI</h2>
Double click on the JAR file or use command "java -jar pdp-assignment5.jar" in terminal
Download the code and run the main method. 
<h2>Use a script</h2>
Use command "java -jar pdp-assignment5.jar -file path-of-script-file" in terminal
<h2>Use the text bases Comand Line interface</h2>
Use command "java -jar pdp-assignment5.jar -text" in terminal and enter commands in the Command line.
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

<h1>Supported Commands</h1>
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


