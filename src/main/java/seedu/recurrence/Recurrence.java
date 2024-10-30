package seedu.recurrence;

import seedu.type.Income;
import seedu.type.IncomeList;
import seedu.type.Spending;
import seedu.type.SpendingList;
import seedu.type.EntryType;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public abstract class Recurrence {
    protected int getLastDayOfMonth(LocalDate newEntry) {
        YearMonth date = YearMonth.of(newEntry.getYear(), newEntry.getMonth());
        return date.atEndOfMonth().getDayOfMonth();
    }

    protected <T extends EntryType> void checkIfDateAltered(T newEntry, LocalDate checkDate, ArrayList<T> list) {
        int dayOfSupposedRecurrence = newEntry.getDayOfRecurrence();
        int lastDayOfNewEntryMonth = getLastDayOfMonth(newEntry.getDate());
        int actualDayToRecur = Math.min(dayOfSupposedRecurrence, lastDayOfNewEntryMonth);
        newEntry.editDateWithLocalDate(checkDate.withDayOfMonth(actualDayToRecur));
        if (!newEntry.getDate().isAfter(LocalDate.now())) {
            list.add(newEntry);
        }
    }

    public abstract void checkIncomeRecurrence(Income recurringIncome, IncomeList incomes);
    public abstract void checkSpendingRecurrence(Spending recurringSpending, SpendingList spendings);
}
