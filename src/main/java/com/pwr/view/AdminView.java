package com.pwr.view;

import com.pwr.model.Client;
import com.pwr.model.Request;
import com.pwr.model.Technician;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AdminView implements IAdminView {

	public void displayNewRequests(List<Request> requests) {
		if(requests.isEmpty()){
			System.out.println("No existing new requests");
			return;
		}
		// Sortowanie zgloszen wedlug daty(najstarsze na poczatku)
		requests.sort(Comparator.comparing(Request::getDate));
		for(Request request : requests){
			StringBuilder output = new StringBuilder();

			// Pobieranie danych zgloszenia
			output.append("Request ID: ").append(request.getId()).append("\n");
			output.append("Issue Description: ").append(request.getIssueDescription()).append("\n");
			output.append("Device Model: ").append(request.getDeviceModel()).append("\n");

			// Pobieranie danych klienta
			Client client = request.getClient();
			output.append("Client ID: ").append(client.getId()).append("\n");
			output.append("Client Personal Data: ").append(client.getPersonalData()).append("\n");
			output.append("Client address: ").append(client.getAddress()).append("\n");
			output.append("Client email: ").append(client.getEmail()).append("\n");

			// Status zgloszenia
			output.append("Status: ").append(request.getStatus().getDescription()).append("\n");

			// Dane serwisanta
			Technician technician = request.getAssignedTechnician();
			if(technician != null){
				output.append("Technician ID: ").append(technician.getId()).append("\n");
			}else {
				output.append("Technician availability: Not assigned\n");
			}

			// Data zgloszenia
			output.append("Date: ").append(request.getDate()).append("\n");

			// Separator miedzy zgloszeniami
			output.append("-----------------------------\n");
			System.out.print(output.toString());
		}
	}


	public void displayAvailableTechnicians(List<Technician> technicians) {
		for(Technician technician : technicians){
			if(technician.isAvailability()){
				// Pobieranie informacji o serwisancie
				String output = "Technician ID: " + technician.getId() + "\n" +
						"Technician Personal Data: " + technician.getPersonalData() + "\n" +
						"Is available: " + technician.isAvailability() + "\n" +
						"-----------------------------\n";
				System.out.print(output);
			}
		}
	}

	public void requestAssignmentInput() {
		// TODO - implement AdminView.requestAssignmentInput
		throw new UnsupportedOperationException();
	}

	@Override
	public int getSelectedId(String message) {
		System.out.println(message);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

	public void notifyTechnicianAvailable(int technicianId) {
		// TODO - implement AdminView.notifyTechnicianAvailable
		throw new UnsupportedOperationException();
	}

}