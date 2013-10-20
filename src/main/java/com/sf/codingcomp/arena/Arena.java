package com.sf.codingcomp.arena;

public class Arena {

	public Character fight(Character character1, Character character2) throws NoStaminaException {
		// TODO implement me
		if (character1.getStamina() <= 0 && character2.getStamina() <= 0){
			throw new NoStaminaException();
		}
		int character1total = character1.getAttack() + (int)(character1.getDefense()*.75);
		int character2total = character2.getDefense() + (int)(character2.getAttack()*.75);
		character1.setStamina(character1.getStamina()-1);
		character2.setStamina(character2.getStamina()-1);
		if (character2total > character1total)
			return character2;
		return character1;
				
	}
}
