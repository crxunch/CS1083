import java.awt.*;
import java.util.*;
public class Movemement {
    public static final Scanner gSCANNER = new Scanner(System.in);
    public static int shapeCount;
    public static int moveCount;
    public static String[] shapes;
    public static int[] sizeOfShape;
    public static String[] colors;
    public static int[] directions;
    public static int[] speeds;
    public static int[] xcoordinates;
    public static int[] ycoordinates;
    public static int width;
    public static int height;
    public static void main(String[] args) {
        System.out.print("Please input width, height of the panel, # of shapes, # of times to move followed by the shape, size, color, direction, and speed of every shape: ");
        width = gSCANNER.nextInt();
        height = gSCANNER.nextInt();
        shapeCount = gSCANNER.nextInt();
        moveCount = gSCANNER.nextInt();
        String raw_data = gSCANNER.nextLine();
        shapes = new String[shapeCount];
        sizeOfShape = new int[shapeCount];
        colors = new String[shapeCount];
        directions = new int[shapeCount];
        speeds = new int[shapeCount];
        xcoordinates = new int[shapeCount];
        ycoordinates = new int[shapeCount];
        getShapeInformation(raw_data);
        DrawingPanel panel = new DrawingPanel(width, height);
        initialPosition(panel);
        showShapesMoving(panel);
    }
    public static void getShapeInformation(String input) {
        String regex = "[\\s]";
        String[] info_array = input.split(regex);
        for (int i = 0; i < shapeCount; i++) {
            shapes[i] = info_array[(5 * i) + 1];
            sizeOfShape[i] = Integer.parseInt(info_array[(5 * i) + 2]);
            colors[i] = info_array[(5 * i) + 3];
            directions[i] = Integer.parseInt(info_array[(5 * i) + 4]);
            speeds[i] = Integer.parseInt(info_array[(5 * i) + 5]);
        }
    }
    public static void initialPosition(DrawingPanel panel) {
        int x_center = width / 2;
        int y_center = height / 2;
        for (int i = 0; i < shapeCount; i++) {
            xcoordinates[i] = x_center - (sizeOfShape[i] / 2);
            ycoordinates[i] = y_center - (sizeOfShape[i] / 2);
        }
        showShapes(panel, true);
        panel.sleep(100);
    }
    public static void showShapes(DrawingPanel panel, boolean show_color) {
        for (int i = 0; i < shapeCount; i++) {
            if (show_color == true) {
                graphicsSetColor(panel, i);
            } else {
                setNoColor(panel);
            }
            if (shapes[i].equals("Square")) {
                showSquare(panel, i, show_color);
            }
            if (shapes[i].equals("Circle")) {
                showCircle(panel, i, show_color);
            }
        }
    }
    public static void showSquare(DrawingPanel panel, int index, boolean set_black) {
        Graphics g = panel.getGraphics();
        g.fillRect(xcoordinates[index], ycoordinates[index], sizeOfShape[index], sizeOfShape[index]);
        if (set_black == true) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawRect(xcoordinates[index], ycoordinates[index], sizeOfShape[index], sizeOfShape[index]);
    }
    public static void showCircle(DrawingPanel panel, int index, boolean set_black) {
        Graphics g = panel.getGraphics();
        g.fillOval(xcoordinates[index], ycoordinates[index], sizeOfShape[index], sizeOfShape[index]);
        if (set_black == true) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawOval(xcoordinates[index], ycoordinates[index], sizeOfShape[index], sizeOfShape[index]);
    }
    public static void graphicsSetColor(DrawingPanel panel, int index) {
        Graphics g = panel.getGraphics();
        String lower_string = colors[index].toLowerCase();
        switch (lower_string) {
            case "red":
                g.setColor(Color.RED);
                break;
            case "blue":
                g.setColor(Color.BLUE);
                break;
            case "pink":
                g.setColor(Color.PINK);
                break;
            case "yellow":
                g.setColor(Color.YELLOW);
                break;
            case "green":
                g.setColor(Color.GREEN);
                break;
            case "magenta":
                g.setColor(Color.MAGENTA);
                break;
            case "orange":
                g.setColor(Color.ORANGE);
                break;
            case "dark_gray":
               g.setColor(Color.DARK_GRAY);
                break;
            case "light_gray":
                g.setColor(Color.LIGHT_GRAY);
                break;
            case "gray":
                g.setColor(Color.GRAY);
                break;
            default:
                g.setColor(Color.BLACK);
        }
    }
    public static void setNoColor(DrawingPanel panel) {
        Graphics g = panel.getGraphics();
        g.setColor(Color.WHITE);
    }
    public static void changePositions() {
        for (int i = 0; i < shapeCount; i++) {
            switch (directions[i]) {
                case 0:
                    xcoordinates[i] -= speeds[i];
                    break;
                case 1:
                    xcoordinates[i] -= speeds[i];
                    ycoordinates[1] -= speeds[i];
                    break;
                case 2:
                    ycoordinates[i] -= speeds[i];
                    break;
                case 3:
                    xcoordinates[i] += speeds[i];
                    ycoordinates[1] -= speeds[i];
                    break;
                case 4:
                    xcoordinates[i] += speeds[i];
                    break;
                case 5:
                    xcoordinates[i] += speeds[i];
                    ycoordinates[1] += speeds[i];
                    break;
                case 6:
                    ycoordinates[i] += speeds[i];
                    break;
                case 7:
                    xcoordinates[i] -= speeds[i];
                    ycoordinates[1] += speeds[i];
                    break;
                default:
                    System.out.println("how did you reach this branch!?");
            }
        }
    }
    public static void showShapesMoving(DrawingPanel panel) {
        for (int i = 0; i < moveCount; i++) {
            showShapes(panel, false);
            changePositions();
            showShapes(panel, true);
            panel.sleep(100);
        }
    }
}
