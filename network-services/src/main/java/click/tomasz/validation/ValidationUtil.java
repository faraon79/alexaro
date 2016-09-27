package click.tomasz.validation;

import click.tomasz.network.model.Data;
import click.tomasz.resources.transfer.LearnData;

public class ValidationUtil {

	public static void validateNetworkName(String name) {
		if (name == null) {
			throw new RuntimeException("Name cannot be null.");
		}else{
			if(name.isEmpty()){
				throw new RuntimeException("Name cannot be empty.");
			}
			if(name.length()>50){
				throw new RuntimeException("Name length cannot exceed 50 characters.");
			}
		}
	}

	public static void validateLearnDataBasic(LearnData data) {
		if (data == null || data.getData() == null) {
			throw new RuntimeException("Data cannot be null.");
		}
		if(data.getData().length == 0){
			throw new RuntimeException("Data cannot be empty.");
		}
	}

	public static void validateDataBasic(Data data) {
		if (data == null || data.getData() == null) {
			throw new RuntimeException("Data cannot be null.");
		}
		if(data.getData().length == 0){
			throw new RuntimeException("Data cannot be empty.");
		}
	}
}
