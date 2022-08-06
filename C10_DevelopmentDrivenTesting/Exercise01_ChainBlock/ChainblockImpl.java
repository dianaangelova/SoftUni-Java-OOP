import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    private Map<Integer, Transaction> transactionMap;

    public ChainblockImpl() {
        this.transactionMap = new HashMap<>();
    }

    public int getCount() {
        return transactionMap.size();
    }

    public void add(Transaction transaction) {
        transactionMap.put(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return contains(transaction.getId());
    }

    public boolean contains(int id) {
        return transactionMap.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {

        if (!transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        Transaction transaction = transactionMap.get(id);
        transaction.setStatus(newStatus);
    }


    public void removeTransactionById(int id) {
        if (!transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        transactionMap.remove(id);
    }


    public Transaction getById(int id) {
        if (!transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return transactionMap.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactionList = transactionMap
                .values()
                .stream()
                .filter(transaction -> transaction.getStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (transactionList.size() == 0) {
            throw new IllegalArgumentException();
        }

        return transactionList;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransactionList = new ArrayList<>();
        getByTransactionStatus(status).forEach(filteredTransactionList::add);
        return filteredTransactionList.stream()
                .map(tr -> tr.getFrom())
                .collect(Collectors.toList());
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransactionList = new ArrayList<>();
        getByTransactionStatus(status).forEach(filteredTransactionList::add);
        List<String> receiversList = new ArrayList<>();
        return filteredTransactionList.stream()
                .map(tr -> tr.getTo())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return transactionMap.values().stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> resultList = transactionMap.values().stream()
                .filter(tr -> tr.getFrom().equals(sender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if (resultList.size() == 0) {
            throw new IllegalArgumentException();
        }
        return resultList;
    }


    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> resultList = transactionMap.values().stream()
                .filter(tr -> tr.getTo().equals(receiver))
                .sorted(Comparator.comparing(Transaction::getAmount).thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        if (resultList.size() == 0) {
            throw new IllegalArgumentException();
        }
        return resultList;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return transactionMap.values().stream()
                .filter(tr -> tr.getStatus().equals(status) && tr.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> resultList = transactionMap.values().stream()
                .filter(tr -> tr.getFrom().equals(sender) && tr.getAmount() >= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (resultList.size() == 0) {
            throw new IllegalArgumentException();
        }
        return resultList;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> resultList = transactionMap.values().stream()
                .filter(tr -> tr.getTo().equals(receiver) && tr.getAmount() >= lo && tr.getAmount() < hi)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        if (resultList.size() == 0) {
            throw new IllegalArgumentException();
        }
        return resultList;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return transactionMap.values().stream()
                .filter(tr -> tr.getAmount() >= lo && tr.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Transaction> iterator() {
        return null;
    }
}
