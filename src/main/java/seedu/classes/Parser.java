package seedu.classes;

import seedu.commands.AddCommand;
import seedu.commands.ByeCommand;
import seedu.commands.Command;
import seedu.commands.DeleteCommand;
import seedu.commands.EditCommand;
import seedu.commands.BudgetCommand;
import seedu.commands.UnknownCommand;
import seedu.commands.listcommands.*;
import seedu.exception.WiagiInvalidInputException;
import seedu.exception.WiagiMissingParamsException;
import seedu.recurrence.DailyRecurrence;
import seedu.recurrence.MonthlyRecurrence;
import seedu.recurrence.Recurrence;
import seedu.recurrence.RecurrenceFrequency;
import seedu.type.Type;
import seedu.recurrence.YearlyRecurrence;

import static seedu.classes.Constants.*;
import static seedu.classes.Constants.LIST_COMMAND_FORMAT;

public class Parser {

    public static Command parse(String fullCommand) {
        String command = fullCommand.split(" ")[0].toLowerCase();

        switch (command) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case AddCommand.COMMAND_WORD:// user input should be in the form add [add type] [amount] [description]...
            return new AddCommand(fullCommand);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(fullCommand);
        case ListAllCommand.COMMAND_WORD:
            return parseListCommand(fullCommand);
        case EditCommand.COMMAND_WORD:
            return new EditCommand(fullCommand);
        case BudgetCommand.COMMAND_WORD:
            return new BudgetCommand(fullCommand);
        default:
            return new UnknownCommand();
        }
    }

    private static Command parseListCommand(String fullCommand) {
        String[] fullCommands = fullCommand.split(" ");
        int commandSize = fullCommands.length;
        // TODO: Command size wont be 0, change to assert?
        if (commandSize == 0) {
            WiagiMissingParamsException e =
                    new WiagiMissingParamsException(INCORRECT_PARAMS_NUMBER + LIST_COMMAND_FORMAT);
            return new InvalidListCommand(e);
        }
        if (commandSize == 1) {
            return new ListAllCommand(fullCommands);
        }
        String firstIndex = fullCommands[1];
        switch (firstIndex) {
        case ListTagsCommand.COMMAND_WORD:
            return new ListTagsCommand(fullCommands);
        case ListSpendingsCommand.COMMAND_WORD:
            return new ListSpendingsCommand(fullCommands);
        case ListIncomesCommand.COMMAND_WORD:
            return new ListIncomesCommand(fullCommands);
        default:
            WiagiInvalidInputException e =
                    new WiagiInvalidInputException(INVALID_CATEGORY + LIST_COMMAND_FORMAT);
            return new InvalidListCommand(e);
        }
    }

    public static Recurrence parseRecurrence(Type entry) {
        RecurrenceFrequency frequency = entry.getRecurrenceFrequency();

        switch (frequency) {
        case DAILY:
            return new DailyRecurrence();
        case MONTHLY:
            return new MonthlyRecurrence();
        case YEARLY:
            return new YearlyRecurrence();
        default:
            return null;
        }
    }
}
