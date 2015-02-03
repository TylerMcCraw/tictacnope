package com.w3bshark.tictacnope;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    List<List<Button>> buttonList;
    List<String> selectedTiles;
    String selectedTilesStr;
    int currentTurn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonList = new ArrayList<>();
        buttonList.add(0, new ArrayList<Button>());
        buttonList.add(1, new ArrayList<Button>());
        buttonList.add(2, new ArrayList<Button>());
        buttonList.get(0).add(0, (Button)findViewById(R.id.button_0_0));
        buttonList.get(0).add(1, (Button)findViewById(R.id.button_0_1));
        buttonList.get(0).add(2, (Button)findViewById(R.id.button_0_2));
        buttonList.get(1).add(0, (Button)findViewById(R.id.button_1_0));
        buttonList.get(1).add(1, (Button)findViewById(R.id.button_1_1));
        buttonList.get(1).add(2, (Button)findViewById(R.id.button_1_2));
        buttonList.get(2).add(0, (Button)findViewById(R.id.button_2_0));
        buttonList.get(2).add(1, (Button)findViewById(R.id.button_2_1));
        buttonList.get(2).add(2, (Button)findViewById(R.id.button_2_2));

        for (int i = 0; i < buttonList.size(); i++) {
            for (int j = 0; j < buttonList.size(); j++) {
                // Set click handlers for all buttons
                buttonList.get(i).get(j).setOnClickListener(onTileClickListener);

                // Set Text size of all buttons
                if (Util.isTablet(getApplicationContext())) {
                    buttonList.get(i).get(j).setTextSize(TypedValue.COMPLEX_UNIT_SP, 42);
                }
                else {
                    buttonList.get(i).get(j).setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                }
            }
        }

        resetBoard();
    }

    private void resetBoard() {
        // Reset selected tiles
        selectedTiles = new ArrayList<>();
        for (int i = 0; i < buttonList.size(); i++) {
            for (int j = 0; j < buttonList.size(); j++) {
                buttonList.get(i).get(j).setText("");
            }
        }

        // Set center button as first play
        Button centerBtn = buttonList.get(1).get(1);
        centerBtn.setText("X");
        centerBtn.setTag(1);
        currentTurn = 1;
        selectedTiles.add("11");
        selectedTilesStr = "11";

        // Start trash talking
        Toast.makeText(getApplicationContext(), getString(R.string.trash_talk), Toast.LENGTH_SHORT).show();
//        Util.showNextTrashTalkToast(getApplicationContext(), currentTurn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void playNextTurn(Button btn) {
        int i = 0;
        int j = 0;
        boolean foundButton = false;
        while (i < buttonList.size()) {
            j = 0;
            while (j < buttonList.size()) {
                if (btn == buttonList.get(i).get(j)) {
                    foundButton = true;
                    break;
                }
                j++;
            }
            if (foundButton) {
                break;
            }
            i++;
        }
        String usersSelection = Integer.toString(i).concat(Integer.toString(j));
        // Keep track of user's play
        selectedTiles.add(usersSelection);
        selectedTilesStr = selectedTilesStr + usersSelection;

        // Each of the following cases represent a set of y,x coordinates (row, column)
        // For now, the app will always play 11 first, which is the center tile
        String mySelection;
        switch (selectedTilesStr) {

            // CONTINUE PLAY ////////////
            case "1100":
            case "1122":
            case "11102200":
            case "11210022":
                mySelection = "20";
                break;
            case "1101":
            case "1110":
                mySelection = "22";
                break;
            case "1121":
            case "1112":
            case "1102":
            case "1120":
                mySelection = "00";
                break;
            case "11002002":
            case "112220021210":
                mySelection = "01";
                break;
            case "11222002":
            case "11020022":
            case "110020020121":
            case "112000222101":
                mySelection = "12";
                break;
            case "11012200":
            case "11120022":
                mySelection = "02";
                break;
            case "11200022":
            case "110200221210":
                mySelection = "21";
                break;

            // COMPUTER WINS //////////
            case "11002001":
            case "11002010":
            case "11002012":
            case "11002021":
            case "11002022":
            case "11222000":
            case "11222001":
            case "11222010":
            case "11222012":
            case "11222021":
            case "111022002001":
            case "111022002021":
            case "112100222010":
            case "112100222012":
                mySelection = "02";
                currentTurn = 8; //Computer WINS
                break;
            case "11012202":
            case "11012210":
            case "11012212":
            case "11012220":
            case "11012221":
            case "11102201":
            case "11102202":
            case "11102212":
            case "11102220":
            case "11102221":
                mySelection = "00";
                currentTurn = 8; //Computer WINS
                break;
            case "11210001":
            case "11210002":
            case "11210010":
            case "11210012":
            case "11210020":
            case "11120001":
            case "11120002":
            case "11120010":
            case "11120020":
            case "11120021":
            case "11020001":
            case "11020010":
            case "11020012":
            case "11020020":
            case "11020021":
            case "11200001":
            case "11200002":
            case "11200010":
            case "11200012":
            case "11200021":
                mySelection = "22";
                currentTurn = 8; //Computer WINS
                break;
            case "110020020110":
            case "110020020112":
            case "110020020122":
            case "111022002002":
            case "111022002012":
            case "1122200212100100":
                mySelection = "21";
                currentTurn = 8; //Computer WINS
                break;
            case "112220021200":
            case "112220021201":
            case "112220021221":
            case "110200221201":
            case "110200221220":
            case "110200221221":
            case "112100222001":
            case "112100222002":
            case "1100200201211222":
            case "1120002221011202":
                mySelection = "10";
                currentTurn = 8; //Computer WINS
                break;
            case "112000222102":
            case "112000222110":
            case "112000222112":
            case "111200220220":
            case "111200220221":
            case "1102002212102120":
                mySelection = "01";
                currentTurn = 8; //Computer WINS
                break;
            case "110122000212":
            case "110122000221":
            case "111200220201":
            case "111200220210":
                mySelection = "20";
                currentTurn = 8; //Computer WINS
                break;
            case "110122000210":
            case "110122000220":
                mySelection = "12";
                currentTurn = 8; //Computer WINS
                break;

            // DRAW ///////////////////
            case "1100200201211210":
                mySelection = "22";
                currentTurn = 10; //DRAW
                break;
            case "1122200212100121":
                mySelection = "00";
                currentTurn = 10; //DRAW
                break;
            case "1102002212102101":
                mySelection = "20";
                currentTurn = 10; //DRAW
                break;
            case "1120002221011210":
                mySelection = "02";
                currentTurn = 10; //DRAW
                break;

            // UNHANDLED //////////////
            default:
                currentTurn = 9; //User found unhandled case, so they win.
                return;
        }

        buttonList.get(Integer.parseInt(mySelection.substring(0, 1))).get(Integer.parseInt(mySelection.substring(1))).setText("X");
        selectedTiles.add(mySelection);
        selectedTilesStr = selectedTilesStr + mySelection;

        currentTurn++;
    }

    private View.OnClickListener onTileClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            Button btn = (Button) v;
            if (!btn.getText().toString().isEmpty()) {
                return;
            }

            // User has played a new turn, so increase the turn count
            currentTurn++;
            btn.setText("O");

            // Let's play our next turn
            playNextTurn(btn);
//            Util.showNextTrashTalkToast(getApplicationContext(), currentTurn);

            if (currentTurn > 8) {
                handleGameOver();
            }
        }
    };

    private void handleGameOver() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.game_over_play_again))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetBoard();
                        dialog.cancel();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        // Game Over, Computer Wins
        if (currentTurn == 9) {
            adb.setTitle(getString(R.string.game_over_comp_wins));
        }
        // Game Over, User Wins
        else if (currentTurn == 10) {
            adb.setTitle(getString(R.string.game_over_user_wins));
        }
        // Game Over, Draw
        else if (currentTurn == 11) {
            adb.setTitle(getString(R.string.game_over_draw));
        }
        adb.show();
    }

//        GridLayout gl = (GridLayout)findViewById(R.id.MainGrid);
//        ViewTreeObserver vto = gl.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {@Override public void onGlobalLayout()
//        {
//            GridLayout gl = (GridLayout) findViewById(R.id.MainGrid);
//            fillview(gl);
//        }});

//    public void fillview(GridLayout gl)
//    {
//        Button buttontemp;
//        int idealChildWidth = (int) ((gl.getWidth()-20*gl.getColumnCount())/gl.getColumnCount());
//        for( int i=0; i< gl.getChildCount();i++)
//        {
//            buttontemp = (Button) gl.getChildAt(i);
//            buttontemp.setWidth(idealChildWidth);
//        }
//    }
}
