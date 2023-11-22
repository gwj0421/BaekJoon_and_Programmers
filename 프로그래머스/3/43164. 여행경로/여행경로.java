import java.util.*;

class Solution {
    private final static String START_AIRLINE = "ICN";

    class Ticket {
        private boolean isUsed;
        private final String arrival;

        public Ticket(String arrival) {
            this.isUsed = false;
            this.arrival = arrival;
        }

        public boolean isUsed() {
            return isUsed;
        }

        public String getArrival() {
            return arrival;
        }

        public void userTicket() {
            isUsed = true;
        }

        public void resetTicket() {
            isUsed = false;
        }
    }


    public String[] solution(String[][] tickets) {
        Map<String, List<Ticket>> airline = new HashMap<>();

        for (int i = 0; i < tickets.length; i++) {
            if (airline.containsKey(tickets[i][0])) {
                airline.get(tickets[i][0]).add(new Ticket(tickets[i][1]));
            } else {
                airline.put(tickets[i][0], new ArrayList<>(List.of(new Ticket(tickets[i][1]))));
            }
        }

        for (List<Ticket> value : airline.values()) {
            value.sort(Comparator.comparing(Ticket::getArrival));
        }

        Stack<Ticket> visit = new Stack<>();
        visit.add(new Ticket(START_AIRLINE));

        return dfs(airline, visit, tickets.length + 1);
    }

    private String[] dfs(Map<String, List<Ticket>> airline, Stack<Ticket> visit, int ticketCnt) {
        if (visit.size() == ticketCnt) {
            return visit.stream().map(Ticket::getArrival).toArray(String[]::new);
        }
        if (airline.containsKey(visit.peek().getArrival())) {
            for (Ticket ticket : airline.get(visit.peek().getArrival())) {
                if (!ticket.isUsed()) {
                    ticket.userTicket();
                    visit.add(ticket);
                    String[] result = dfs(airline, visit, ticketCnt);
                    if (result != null) {
                        return result;
                    }
                    ticket.resetTicket();
                    visit.pop();
                }
            }
        }
        return null;
    }
}