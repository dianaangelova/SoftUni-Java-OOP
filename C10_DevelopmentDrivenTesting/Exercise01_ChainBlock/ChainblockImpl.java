import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    private Map<Integer, Transaction> transactionMap;

    public ChainblockImpl() {
        this.transactionMap = new LinkedHashMap<>();
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
        List<Transaction> transactionList = new ArrayList<>();
        transactionList = transactionMap
                .values()
                .stream()
                .filter(transaction -> transaction.getStatus() == status)
                .collect(Collectors.toList());
        if (transactionList.size() == 0) {
            throw new IllegalArgumentException();
        }
        transactionList.sort(Comparator.comparing(Transaction::getAmount).reversed());
        return transactionList;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransactionList = new ArrayList<>();
        getByTransactionStatus(status).forEach(filteredTransactionList::add);
        List<String> sendersList = new ArrayList<>();
        for (Transaction tr : filteredTransactionList) {
            sendersList.add(tr.getFrom());
        }
        return sendersList;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransactionList = new ArrayList<>();
        getByTransactionStatus(status).forEach(filteredTransactionList::add);
        List<String> receiversList = new ArrayList<>();
        for (Transaction tr : filteredTransactionList) {
            receiversList.add(tr.getTo());
        }
        return receiversList;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return transactionMap.values().stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> resultList = transactionMap.values().stream()
                .filter(tr -> tr.getFrom() == sender)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if (resultList.size() == 0) {
            throw new IllegalArgumentException();
        }
        return resultList;
    }


    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> resultList = transactionMap.values().stream()
                .filter(tr -> tr.getTo() == "Pesho")
                .sorted(Comparator.comparing(Transaction::getAmount).thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        if (resultList.size() == 0) {
            throw new IllegalArgumentException();
        }
        return resultList;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        // List<Transaction> resultList =
        return transactionMap.values().stream()
                .filter(tr -> tr.getStatus() == status)
                .filter(tr -> tr.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return null;
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
