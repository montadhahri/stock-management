package org.montadhr.stockManagement;


public class TypeScreenFactory {
	
	public enum TYPE{
		MACHINE("Machine"),EQUIPEMENT("Equipement"),SOUSEQUIPEMENT("SousEquipement");
		
		protected String type;

		private TYPE(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return type;
		}
	}
	
	private TYPE typeScreen;
	
	private Long id;

	public TYPE getTypeScreen() {
		return typeScreen;
	}

	public void setTypeScreen(TYPE typeScreen) {
		this.typeScreen = typeScreen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
