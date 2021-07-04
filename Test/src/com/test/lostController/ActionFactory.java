package com.test.lostController;

import com.test.lostController.action.Action;
import com.test.lostController.action.LostAnimalAction;
import com.test.lostController.action.LostMainAction;
import com.test.lostController.action.RegisterLostAction;
import com.test.lostController.action.RegisterLostArticleAction;
import com.test.lostController.action.RegisterReplyAction;

public class ActionFactory {
	
	private ActionFactory() {
		super();
	}
	
	private static ActionFactory af = new ActionFactory();
	
	//singleton
	public static ActionFactory getInstance() {
		return af;
	}
	
	public Action getAction(String cmd) {
		Action action = null;

		if(cmd.equals("lostMain")) {
			action = new LostMainAction();
		}else if(cmd.equals("registerLost")) {
			action = new RegisterLostAction();
		}else if(cmd.equals("registerLostArticle")) {
			action = new RegisterLostArticleAction();
		}else if(cmd.equals("lostAnimal")) {
			action = new LostAnimalAction();
		}else if(cmd.equals("registerReply")) {
			action = new RegisterReplyAction();
		}
		
		return action;
	}
	

}
