# Drawing Application

##Instructions
1. Launch a CLI and navigate to the project folder location.
2. Run `./mvnw clean package` for Unix systems and `./mvnw.cmd clean package` for Batch.
3. Navigate to the executable jar file in the "target" folder or search for DrawingApplication-1.0.jar
4. Execute the following
command at the file location.
    
    `java -jar DrawingApp-1.0.jar`

##Assumptions
* The minimum canvas size is width = 1, height = 1
* If any of the (x,y) input coordinates are out of the boundary of the canvas, the command will not execute
* If Command **B** is executed on a line or boundary of a box, that line or boundary will be colored instead
