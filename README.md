# Image-Manipulation-and-Enhancement
This project is an Image Manipulation and Enhancement tool developed as a part of coursework for the course Programming Design and Paradigms. 

<h1>Introduction</h1>
This is a java tool built using MVC architecture to perform some basic operations on images. It supports text-based user interface where in a user can enter commands as well as a Graphical User Interface. Various image manipulation operations such as Sepia, Blur, Sharpen, Level Adjust , Color COrrect, Luma , Value etc are supported.

The focus of this project was to develop a tool while adhering to an elite coding design, following the SOLID principles and maintaining good readibility..

Supported Operations in Graphical View and How To Use Them
Load
Select File at the top left corner of the screen.
Choose Load from the menu.
Pick a file to load from the file chooser.
The GUI will show the image.
Save
Select File at the top left corner of the screen.
Choose Save from the menu.
Pick a place in the file chooser to save the image, and type in the name and extension for the image. Valid extensions include .jpg, .png, and .ppm.
Compress
Select File at the top left corner of the screen.
Choose Compress from the menu.
A pop up will appear. Enter a number from 0-100.
An error pop up will appear if the user does not enter a number from 0-100.
The image along with its histogram will show up in the GUI.
Visualize Red Component
Select Visualize from the navigation bar.
Choose R-Component from the menu.
The image along with its histogram will show up in the GUI.
Visualize Green Component
Select Visualize from the navigation bar.
Choose G-Component from the menu.
The image along with its histogram will show up in the GUI.
Visualize Blue Component
Select Visualize from the navigation bar.
Choose B-Component from the menu.
The image along with its histogram will show up in the GUI.
Visualize Intensity
Select Visualize from the navigation bar.
Choose Intensity from the menu.
A percentage of the image as toggled in the split slider will be the intensity of the image.
Press the cancel button to cancel the operation or press the confirm button to apply the intensity to the whole image.
The image along with its histogram will show up in the GUI.
Visualize Luma
Select Visualize from the navigation bar.
Choose Luma from the menu.
A percentage of the image as toggled in the split slider will be the luma of the image.
Press the cancel button to cancel the operation or press the confirm button to apply the luma to the whole image.
The image along with its histogram will show up in the GUI.
Visualize Value
Select Visualize from the navigation bar.
Choose Value from the menu.
A percentage of the image as toggled in the split slider will be the value of the image.
Press the cancel button to cancel the operation or press the confirm button to apply the value to the whole image.
The image along with its histogram will show up in the GUI.
Flip Horizontal
Select Flip from the navigation bar.
Choose Horizontal from the menu.
The image along with its histogram will show up in the GUI.
Flip Vertical
Select Flip from the navigation bar.
Choose Vertical from the menu.
The image along with its histogram will show up in the GUI.
Blur
Select Filters from the navigation bar.
Choose Blur from the menu.
A percentage of the image as toggled in the split slider will be blurred.
Press the cancel button to cancel the operation or press the confirm button to apply the blur to the whole image.
The image along with its histogram will show up in the GUI.
Sharpen
Select Filters from the navigation bar.
Choose Sharpen from the menu.
A percentage of the image as toggled in the split slider will be sharpened.
Press the cancel button to cancel the operation or press the confirm button to apply the sharpen to the whole image.
The image along with its histogram will show up in the GUI.
Brighten/Darken
Select Filters from the navigation bar.
Choose Brighten from the menu.
A pop up will appear. Enter a positive integer to brighten the image and a negative integer to darken the image.
An error pop up will appear if the user does not enter an integer.
The image along with its histogram will show up in the GUI if valid value is entered.
Sepia
Select Filters from the navigation bar.
Choose Sepia from the menu.
A percentage of the image as toggled in the split slider will have the sepia operation performed on it.
Press the cancel button to cancel the operation or press the confirm button to apply sepia to the whole image.
The image along with its histogram will show up in the GUI.
Color Correct
Select Filters from the navigation bar.
Choose Color Correct from the menu.
A percentage of the image as toggled in the split slider will have the color correct operation performed on it.
Press the cancel button to cancel the operation or press the confirm button to apply color correct to the whole image.
The image along with its histogram will show up in the GUI.
Level Adjust
Select Level Adjust from the navigation bar.
Choose Level Adjust from the menu.
A pop up will appear with places for the user to enter black, white, and mid values.
An error pop up will appear if the user does not enter a integer or if other levels adjust conditions for the values are not met.
A percentage of the image as toggled in the split slider will have the levels adjustment operation performed on it if there is no error.
Press the cancel button to cancel the operation or press the confirm button to apply level adjust to the whole image.
The image along with its histogram will show up in the GUI.
Split
A slider controls the split for the percentage of the image that has been operated on and the original image prior to operation.
The location of this slider is in a new window once the user chooses an operation that can be previewed.
Toggling of the slider allows the modified image to be shown quickly in the preview.
The user can cancel the operation using the cancel button or confirm the operation using the confirm button.
Once the operation is confirmed, the preview will close, and the operation will be applied to the whole image.
Supported Commands
load image-path image-name
Conditions:
Path name must be a valid path where that image exists. Only ppm,png,jpg,jpeg image formats are supported.
Image name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Example:
load Road.jpg road
save image-path image-name
Conditions:
Path name must be a valid path where that image can be saved.
Image name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Only ppm,png,jpg,jpeg image formats are supported. If no extension is provided, default is saved as jpg.
Example:
save ./ road-horizontal.jpg
red-component image-name dest-image-name
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Example:
red-component road road-red
green-component image-name dest-image-name
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Example:
green-component road road-green
blue-component image-name dest-image-name
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Example:
blue-component road road-blue
blur image-name dest-image-name split split-percent
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
split split-percent is optional; if not provided, it is 0 by default.
Split percent if provided must be a double between 0 - 100
Examples:
blur road road-blur
blur road road-blur-50 split 50
sharpen image-name dest-image-name split split-percent
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
split split-percent is optional; if not provided, it is 0 by default.
Split percent if provided must be a double between 0 - 100
Examples:
sharpen road road-sharpen
sharpen road road-sharpen-70 split 70
sepia image-name dest-image-name split split-percent
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
split split-percent is optional; if not provided, it is 0 by default.
Split percent if provided must be a double between 0 - 100
Examples:
sepia road road-sepia
sepia road road-sepia-30 split 30
horizontal-flip image-name dest-image-name
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Example:
horizontal-flip road road-horizontal
vertical-flip image-name dest-image-name
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Example:
vertical-flip road road-vertical
brighten increment image-name dest-image-name
Conditions:
Increment must be an image
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Examples:
brighten road road-bright 50
brighten road road-dark -50
rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue
Conditions:
Increment must be an image
Image-name must be a valid name of image that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name,dest-image-red,dest-image-green,dest-image-blue name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Example:
rgb-split roadsp r g b
rgb-combine image-name dest-image-name-red dest-image-name-green dest-image-name-blue
Conditions:
Increment must be an image
Image-name must be a valid name of image that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
red-image, blue-image, green-image must be a valid name of image that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Example:
rgb-combine road-combine roadR roadG roadB
luma-component image-name dest-image-name split split-percent
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
split split-percent is optional; if not provided, it is 0 by default.
Split percent if provided must be a double between 0 - 100
Examples:
luma-component road road-luma
luma-component road road-luma-30 split 30
value-component image-name dest-image-name split split-percent
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
split split-percent is optional; if not provided, it is 0 by default.
Split percent if provided must be a double between 0 - 100
Examples:
value-component road road-value
value-component road road-value-40 split 40
intensity-component image-name dest-image-name split split-percent
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
split split-percent is optional; if not provided, it is 0 by default.
Split percent if provided must be a double between 0 - 100
Examples:
intensity-component road road-intensity
intensity-component road road-intensity-40 split 40
run script-file-path
Conditions:
Script-file-path must be a valid path to a file containing commands.
Example:
run scriptfile.txt
compress percentage image-name dest-image-name
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Compress percent must be in valid range 0 - 100
Example:
compress 70 road road-compressed
color-correct image-name dest-image-name split split-percent
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
split split-percent is optional; if not provided, it is 0 by default.
Split percent if provided must be a double between 0 - 100
Examples:
color-correct road road-color-correct
color-correct road road-color-correct-50 split 50
histogram image-name dest-image-name
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
Example:
histogram road road-histogram
levels-adjust b m w image-name dest-image-name split split-percent
Conditions:
Image-name must be a valid name that was previously loaded using load command. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
dest-image-name name must be a valid name. It cannot contain illegal characters(!@#$%^&*)(,/?<>).
split split-percent is optional; if not provided, it is 0 by default.
Split percent if provided must be a double between 0 - 100
0< b < m < w < 255
Examples:
level-adjust 100 150 200 road road-level-adjust
level-adjust 100 150 200 road road-level-adjust-50 split 50

<h1>Access</h1>
This project was a part of coursework for the graduate course Programming Design and Paradigms and sharing the source code online would be a violation of academic integrity. However, the code can be made available on demand.
