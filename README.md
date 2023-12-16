# NQueensSolver
NQueens Solver project with Multi-Threading using Java


# Overall Approach for N Queens Problem:
The overall approach involves creating a GUI for user interaction, initiating a thread for
each column to solve the N Queens Problem concurrently, and providing a visual
representation of the chessboard along with a table to display the solutions found by
each thread. Backtracking is used to explore the solution space, and the program
handles concurrent execution and synchronization.
## How Each Class Work
1. GUI Initialization (GUIMain class):
  ○ A graphical user interface (GUI) is created using the Swing framework.
  ○ The user is prompted to enter the size of the chessboard (N)
    through a text field.
  ○ The "Submit" button triggers the initiation of the solver.
2. Main Class (Main class):
  ○ Creates the main frame and initializes necessary components.
  ○ Upon "Submit" button click:
  ■ Validates the input to ensure it's a positive integer.
  ■ Initializes a thread group (MyThreadGroup), a mutex,
    and an atomic integer to coordinate and track solutions.
  ■ Disposes of the main frame to allow the solver threads
    to run independently.
  ■ Creates a chessboard (MyTable), and for each column in
    the board, creates a thread associated with a NQueensSolver instance.
  ■ Start each thread.
3. Table for Displaying Solutions (MyTable class):
  ○ Extends JFrame to create a table for displaying thread
    outputs.
  ○ Utilizes a JTable with a DefaultTableModel to dynamically add
    rows.
  ○ Provides methods to add rows to the table (addRow).
4. Utilities Class:
  ○ Contains utility methods for positioning frames on the screen
    (centerFrameOnScreen, moveFrameToLeftTop, moveFrameToRightTop, etc.).
  ○ Includes a delay method for thread synchronization (delay).
5. N Queens Solver (NQueensSolver class):
  ○ Implements the Runnable interface for concurrent execution.
  ○ Receives a mutex, thread group, chessboard, board size,
    atomic integer for solution count, and a table for display.
  ○ Uses backtracking to solve the N Queens Problem concurrently:
    ■ solveNQUtil is a recursive method exploring the
      solution space.
    ■ The main run method handles the overall flow of the
      solver, updates the display table, and handles interruptions.
   
6. Chess Board Representation (ChessBoard class):
   
  ○ Represents the chessboard visually using a GUI.
  ○ Utilizes a 2D array to represent the logical state of the
    chessboard.
  ○ Provides methods to set and empty buttons on the
    chessboard (setButton, emptyButton).
  ○ Initializes the graphical representation of the chessboard with
    a Swing-based GUI.
