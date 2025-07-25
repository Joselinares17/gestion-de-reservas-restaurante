import repository.ReservationRepository;
import service.IReservationService;
import service.ReservationService;
import view.TerminalView;

public class Main {
    public static void main(String[] args) {
        ReservationRepository reservationRepository = new ReservationRepository();
        IReservationService reservationService = new ReservationService(reservationRepository);

        TerminalView terminalView = new TerminalView(reservationService);

        terminalView.start();
    }
}