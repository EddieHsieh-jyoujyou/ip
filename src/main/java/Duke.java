/**
 * Entry point of Duke project.
 * This class provide user to type command, by identifying different prefix of command, call different method of
 * temp.DukeCommandHandler.
 * Refactor at Mar 1st.
 * Java project should follow the SOLID principle.
 * S: single-responsibility principle
 * O: open-closed principle
 * L: Liskov substitution principle
 * I: interface segregation principle
 * D: dependency inversion principle
 */
public class Duke {
    public static void main(String[] args) {

        Bot dukeChatBot = new DukeChatBot();
        dukeChatBot.powerOn();

    }

}
