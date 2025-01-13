package com.pwr.view;

import com.pwr.model.Client;
import com.pwr.model.Request;
import com.pwr.model.Technician;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ClientView implements IClientView {

	public void displayNotification(String message) {
		System.out.println("New notification: " + message);
	}

	@Override
	public void displayRepairs(List<Request> requests) {
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
				output.append("Technician: Not assigned\n");
			}

			// Data zgloszenia
			output.append("Date: ").append(request.getDate()).append("\n");

			// Separator miedzy zgloszeniami
			output.append("-----------------------------\n");
			System.out.print(output.toString());
		}
	}

	public int getSelectedRepairId() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

	@Override
	public boolean getClientChoice(String message) {
		System.out.println(message);
		Scanner scanner = new Scanner(System.in);
		String clientChoice = scanner.next();
		if(clientChoice.equals("yes")){
			return true;
		} else if(clientChoice.equals("no")){
			return false;
		}
		return false;
	}


	public String enterReviewDescription() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public int enterReviewRate() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

}