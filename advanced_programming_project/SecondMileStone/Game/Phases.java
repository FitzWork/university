package SecondMileStone.Game;

import java.awt.*;

public class Phases {
    public final Brick[] bricks = new Brick[24];
    public final Color defaultColor;
    private final int moveLimit,
            gameId;
    public int turn,
            phase = 1,
            moveCount,
            count_black = 0,
            count_white = 0;
    private String fieldsToColor,
            colorChange,
            nextStep;
    public boolean successful = false,
            mill = false,
            selected = false;

    public Phases(int gameId, int startColor) {
        this.gameId = gameId;
        this.turn = startColor;
        if (startColor == Color.BLACK.getRGB()) {
            moveCount = 0;
            moveLimit = 18;
        }
        else {
            moveCount = 1;
            moveLimit = 19;
        }

        for (int i = 0; i < 24; i++)
            bricks[i] = new Brick();
        addNeighborsToBricks();
        defaultColor = bricks[0].getBackground();
    }

    public int getGameId() {return gameId;}

    private void addNeighborsToBricks() {
        bricks[0]. addNeighbors(bricks, new int[] {1, 9},           new int[] {1, 2, 9, 21});
        bricks[1]. addNeighbors(bricks, new int[] {0, 2, 4},        new int[] {0, 2, 4, 7});
        bricks[2]. addNeighbors(bricks, new int[] {1, 14},          new int[] {0, 1, 14, 23});
        bricks[3]. addNeighbors(bricks, new int[] {4, 10},          new int[] {4, 5, 10, 18});
        bricks[4]. addNeighbors(bricks, new int[] {1, 3, 5, 7},     new int[] {3, 5, 1, 7});
        bricks[5]. addNeighbors(bricks, new int[] {4, 13},          new int[] {3, 4, 13, 20});
        bricks[6]. addNeighbors(bricks, new int[] {7, 11},          new int[] {7, 8, 11, 15});
        bricks[7]. addNeighbors(bricks, new int[] {4, 6, 8},        new int[] {6, 8, 1, 4});
        bricks[8]. addNeighbors(bricks, new int[] {7, 12},          new int[] {6, 7, 12, 17});
        bricks[9]. addNeighbors(bricks, new int[] {0, 10, 21},      new int[] {10, 11, 0, 21});
        bricks[10].addNeighbors(bricks, new int[] {3, 9, 11, 18},   new int[] {9, 11, 3, 18});
        bricks[11].addNeighbors(bricks, new int[] {6, 10, 15},      new int[] {9, 10, 6, 15});
        bricks[12].addNeighbors(bricks, new int[] {8, 13, 17},      new int[] {13, 14, 8, 17});
        bricks[13].addNeighbors(bricks, new int[] {5, 12, 14, 20},  new int[] {12, 14, 5, 20});
        bricks[14].addNeighbors(bricks, new int[] {2, 13, 23},      new int[] {12, 13, 2, 23});
        bricks[15].addNeighbors(bricks, new int[] {11, 16},         new int[] {16, 17, 6, 11});
        bricks[16].addNeighbors(bricks, new int[] {15, 17, 19},     new int[] {15, 17, 19, 22});
        bricks[17].addNeighbors(bricks, new int[] {12, 16},         new int[] {15, 16, 8, 12});
        bricks[18].addNeighbors(bricks, new int[] {10, 19},         new int[] {19, 20, 3, 10});
        bricks[19].addNeighbors(bricks, new int[] {16, 18, 20, 22}, new int[] {18, 20, 16, 22});
        bricks[20].addNeighbors(bricks, new int[] {13, 19},         new int[] {18, 19, 5, 13});
        bricks[21].addNeighbors(bricks, new int[] {9, 22},          new int[] {22, 23, 0, 9});
        bricks[22].addNeighbors(bricks, new int[] {19, 21, 23},     new int[] {21, 23, 16, 19});
        bricks[23].addNeighbors(bricks, new int[] {14, 22},         new int[] {21, 22, 2, 14});
    }
    /**
     * sent index to the specific phase dependence on the actual turn;
     * @param index getting concrete location to check
     */
    public void check(int index) {
        if (phase == 1)
            phase1(index);
        if (phase == 2)
            phase2(index);
        if (phase == 3)
            phase3(index);
    }
    /**
     * @return true if the move was correct
     */
    public boolean successful() {return successful;}
    /**
     * @return the upcoming step and color and the field(s) to color
     */
    public String getNextStep() {return nextStep;}
    public String getAllFields() {return fieldsToColor;}
    public String getColorChange() {return colorChange;}


    /**
     * @return if all bricks of turn in a mill
     */
    private boolean allBricksInMill() {
        Color help = turn == Color.BLACK.getRGB() ? Color.WHITE : Color.BLACK;
        for (Brick brick : bricks) {
            if (brick.getBackground() == help)
                if (!brick.inMill())
                    return false;
        }
        return true;
    }
    /**
     * proofing if any neighbor is free
     * @param index where the neighbors from
     */
    private boolean neighborsFree(int index) {
        Brick[] neighbors = bricks[index].getNeighbors();
        for (Brick neighbor : neighbors)
            if(neighbor.getBackground() == defaultColor)
                return true;
        return false;
    }
    /**
     * pointing out the neighbors and color them green if they have default color
     * @param index where the neighbors from
     */
    private void colorNeighbors(int index) {
        selected = true;
        bricks[index].setBackground(Color.BLUE);
        Brick[] neighbors = bricks[index].getNeighbors();
        for (Brick neighbor : neighbors)
            if (neighbor.getBackground() == defaultColor)
                neighbor.setBackground(Color.GREEN);

        fieldsToColor = "NEIGHBORS_" + index;
        colorChange = String.valueOf(Color.GREEN.getRGB());
        nextStep = "PLACE";
        successful = true;
    }
    /**
     * Setting the brick on the position index and check if there would be just two bricks of one color in phase 3,
     * if yes it would end the game.
     * @param index get background default color
     */
    private void reset(int index) {
        mill = false;
        if (bricks[index].getBackground() == Color.BLACK)
            count_black--;
        if (bricks[index].getBackground() == Color.WHITE)
            count_white--;

        bricks[index].setBackground(defaultColor);

        moveCount++;

        fieldsToColor = String.valueOf(index);
        colorChange = String.valueOf(defaultColor.getRGB());
        if (phase == 1)
            nextStep = "SET";
        else
            nextStep = "TAKE";

        turn = turn == Color.BLACK.getRGB() ? Color.WHITE.getRGB() : Color.BLACK.getRGB();

        if (phase == 3 && (count_black == 2 || count_white == 2))
            end();
    }
    /**
     * @param index would be colored green same as every other default colored brick
     */
    private void colorAllFree(int index) {
        selected = true;
        bricks[index].setBackground(Color.BLUE);
        for (Brick brick : bricks)
            if (brick.getBackground() == defaultColor)
                brick.setBackground(Color.GREEN);

        fieldsToColor = "ALLFREE_" + index;
        colorChange = String.valueOf(Color.GREEN.getRGB());
        nextStep = "PLACE";
        successful = true;
    }
    /**
     * coloring all green colored bricks on default color
     */
    private void recolorAllGreen() {
        for (Brick brick : bricks) {
            if (brick.getBackground() == Color.GREEN || brick.getBackground() == Color.BLUE)
                brick.setBackground(defaultColor);
        }
    }

    /**
     * Resetting variable selected.
     * Change everything to again if index is same like last one.
     * Otherwise, color to change would bei set on turn color, because the selected brick would be paced.
     * And if a mill would come the next step would be "delete" if not he would be "take".
     * @param index is needed for checking if it would be same color as lost round and to color correct
     */
    private void selected(int index) {
        selected = false;
        fieldsToColor = "ALLGREEN_" + index;
        colorChange = String.valueOf(turn);

        recolorAllGreen();

        bricks[index].setBackground(new Color(turn));

        if (bricks[index].inMill() && bricks[index].getBackground() != Color.BLUE) {
            mill = true;
            nextStep = "DELETE";
        }
        else if (bricks[index].getBackground() != Color.BLUE) {
            moveCount++;
            nextStep = "TAKE";
            turn = turn == Color.BLACK.getRGB() ? Color.WHITE.getRGB() : Color.BLACK.getRGB();
        }
        else {
            nextStep = "AGAIN";
            colorChange = String.valueOf(turn);
            fieldsToColor = String.valueOf(index);
        }
    }
    /**
     * If mill is set, then it would be reset the brick at position index.
     * If not, then will neighborsFree() getting checked.
     * If there is no available neighbor then next step would be "again".
     * If not, the brick on position index would be colored green with his neighbors or all available bricks in phase 3.
     * @param index is needed to color the specific brick
     */
    private void notSelected(int index) {
        if(!mill) {
            if (phase == 2 && neighborsFree(index)) {
                if (turn == Color.BLACK.getRGB() && bricks[index].getBackground() == Color.BLACK)
                    colorNeighbors(index);
                else if (turn == Color.WHITE.getRGB() && bricks[index].getBackground() == Color.WHITE)
                    colorNeighbors(index);
            }
            else if (phase == 3) {
                if (moveCount % 2 == 0 && count_black == 3)
                    colorAllFree(index);
                else if (moveCount % 2 == 1 && count_white == 3)
                    colorAllFree(index);
                else {
                    if (neighborsFree(index)){
                        if (moveCount % 2 == 0 && bricks[index].getBackground() == Color.BLACK)
                            colorNeighbors(index);
                        else if (moveCount % 2 == 1 && bricks[index].getBackground() == Color.WHITE)
                            colorNeighbors(index);
                    }
                    else end();
                }
            }
            else end();
        } else {
            if(allBricksInMill() && moveCount % 2 == 0 && bricks[index].getBackground() == Color.WHITE)
                reset(index);
            else if(allBricksInMill() && moveCount % 2 == 1 && bricks[index].getBackground() == Color.BLACK)
                reset(index);
            else if(moveCount % 2 == 0 && bricks[index].getBackground() == Color.WHITE && !bricks[index].inMill())
                reset(index);
            else if(moveCount % 2 == 1 && bricks[index].getBackground() == Color.BLACK && !bricks[index].inMill())
                reset(index);
        }
    }


    /**
     * First proofing if move limit is reached, if yes it would change phase to 2.
     * If not, then mill would be proofed, if yes then the brick at position index would get set to default color.
     * If not, then brick on position index would be colored in turn color and then mill would be checked and if
     * necessary set, so that next step would be "delete" otherwise he would be "set".
     * @param index is the given index of the clicked brick from the client
     */
    public void phase1(int index) {
        if (moveCount < moveLimit) {
            if (!mill) {
                if (bricks[index].getBackground() == defaultColor) {
                    fieldsToColor = String.valueOf(index);
                    colorChange = String.valueOf(turn);

                    bricks[index].setBackground(new Color(turn));

                    if (turn == Color.BLACK.getRGB()) count_black++;
                    else count_white++;


                    if (bricks[index].inMill()) {
                        mill = true;
                        nextStep = "DELETE";
                    }
                    else {
                        nextStep = "SET";
                        turn = turn == Color.BLACK.getRGB() ? Color.WHITE.getRGB() : Color.BLACK.getRGB();
                        moveCount++;
                    }
                    successful = true;
                }
//                new not proved
                else {
                    nextStep = "AGAIN";
                    fieldsToColor = "NOTHING";
                }
            } else {
                if(allBricksInMill()
                        && turn == Color.BLACK.getRGB()
                        && bricks[index].getBackground() == Color.WHITE) {
                    reset(index);
                }
                else if(allBricksInMill()
                        && turn == Color.WHITE.getRGB()
                        && bricks[index].getBackground() == Color.BLACK) {
                    reset(index);
                }
                else if(turn == Color.BLACK.getRGB()
                        && bricks[index].getBackground() == Color.WHITE
                        && !bricks[index].inMill()) {
                    reset(index);
                }
                else if(turn == Color.WHITE.getRGB()
                        && bricks[index].getBackground() == Color.BLACK
                        && !bricks[index].inMill()) {
                    reset(index);
                } else {
                    nextStep = "AGAIN";
                    fieldsToColor = "NOTHING";
                }
            }
        }

        if (moveCount == moveLimit) {
            nextStep = "TAKE";
            phase = 2;
        }
    }
/**
     * First proofing if selected is set and if the brick how is given with index would be correct, if yes selected()
     * would get in progress, otherwise notSelected() would get in progress.
     * In the end it checks if any color count is set down to 3.
     * @param index is the given index of the clicked brick from the client
     */
    public void phase2(int index) {
        if(selected && (bricks[index].getBackground() == Color.GREEN || bricks[index].getBackground() == Color.BLUE)){
            selected(index);
        }
        else
            notSelected(index);

        if (count_black == 3 || count_white == 3)
            phase = 3;

        successful = true;
    }
    /**
     * First proofing if selected is set and if the brick how is given with index would be correct, if yes selected()
     * would get in progress, otherwise notSelected() would get in progress.
     * @param index is the given index of the clicked brick from the client
     */
    public void phase3(int index) {
        if(selected && (bricks[index].getBackground() == Color.GREEN || bricks[index].getBackground() == Color.BLUE)){
            selected(index);
        } else
            notSelected(index);

        successful = true;
    }



    private void end() {
        nextStep = "END";
    }
}
