# RoboTarta
Command the Adafruit Motor Shield for Arduino from a Raspberry Pi using libraries written in Java.


# The project
I bought an [Adafruit Motor Shield] to use with a small rover [DAGU 4WD] motors controlled by Raspberry Pi using an application written in Java. 
with disappointment i discovered that adafruit libraries are written only in C to work with the Arduino stack. 
So I decided to rewrite them in Java to use in my project.


# Tools
Over the web, there's plenty of project and tutorial written in Python or C, based on their respective libraries RPi.GPIO and WiringPi. Java is less used but there are libraries to use the Gpio of Raspberry, thanks to [pi4j project].
The Adafruit Motor Shield use two Texas Instruments L293D to drive motors and a SN74HC595 shift register for driving the two L293D. Here the scheme: [Schema] img/schema motorshield.png


[Adafruit Motor Shield]: <https://www.adafruit.com/products/81>
[DAGU 4WD]: http://www.dagurobot.com/goods.php?id=55
[pi4j project]: http://pi4j.com/index.html

