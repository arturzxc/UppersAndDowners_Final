/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Comparator;

class SortWhoStarts implements Comparator<Player> {

    @Override
    public int compare(Player player1Roll, Player player2Roll) {
        return player2Roll.getStartPosition()-player1Roll.getStartPosition();
    }
}