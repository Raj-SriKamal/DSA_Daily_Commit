
package Graph;

import java.util.*;

public class AccountsMerge {

    public List<List<String>> accountsMerge(
        List<List<String>> accounts
    ) {
        int n = accounts.size();

        int[] parent = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Map each email to the account index where it first appeared
        Map<String, Integer> emailToAccount = new HashMap<>();

        // Step 1: Merge accounts that share emails
        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);

            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);

                if (emailToAccount.containsKey(email)) {
                    int otherAccount = emailToAccount.get(email);
                    union(i, otherAccount, parent, rank);
                } else {
                    emailToAccount.put(email, i);
                }
            }
        }

        // Step 2: Group emails by their final DSU root
        Map<Integer, List<String>> mergedEmails = new HashMap<>();

        for (String email : emailToAccount.keySet()) {
            int accountIndex = emailToAccount.get(email);
            int root = find(accountIndex, parent);

            mergedEmails
                .computeIfAbsent(root, k -> new ArrayList<>())
                .add(email);
        }

        // Step 3: Sort emails and prepend the account name
        List<List<String>> result = new ArrayList<>();

        for (Map.Entry<Integer, List<String>> entry
                : mergedEmails.entrySet()) {

            int root = entry.getKey();
            List<String> emails = entry.getValue();

            Collections.sort(emails);

            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(accounts.get(root).get(0));
            mergedAccount.addAll(emails);

            result.add(mergedAccount);
        }

        return result;
    }

    private int find(int node, int[] parent) {
        if (parent[node] != node) {
            parent[node] = find(parent[node], parent);
        }

        return parent[node];
    }

    private void union(
        int u,
        int v,
        int[] parent,
        int[] rank
    ) {
        int rootU = find(u, parent);
        int rootV = find(v, parent);

        if (rootU == rootV) {
            return;
        }

        if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
    }

    public static void main(String[] args) {
        AccountsMerge solution = new AccountsMerge();

        List<List<String>> accounts = new ArrayList<>();

        accounts.add(new ArrayList<>(Arrays.asList(
            "John", "johnsmith@mail.com", "john_newyork@mail.com"
        )));
        accounts.add(new ArrayList<>(Arrays.asList(
            "John", "johnsmith@mail.com", "john00@mail.com"
        )));
        accounts.add(new ArrayList<>(Arrays.asList(
            "Mary", "mary@mail.com"
        )));
        accounts.add(new ArrayList<>(Arrays.asList(
            "John", "johnnybravo@mail.com"
        )));

        System.out.println(solution.accountsMerge(accounts));
    }
}
