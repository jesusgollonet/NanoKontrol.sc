/*
- straight use of the NanoKontrol for Supercollider
- I'd like something like 

	n = NanoKontrol.new
	// don't take scenes into account
	n.fader2.addResponder(\respName, {
		
	});
	
	or better yet
	n.fader2.onChanged({|vel|
		
	})
	
	n.topButton1.onPressed({
		
	})
	to make it 
	// 
	
	

*/
/*
	TODO: 
		- add the rest of the controllers
		- make the syntax more intuitive (n.button.onChanged)
		
*/

NanoKontrol {

	var <faders;
	var controllers;
	var responders;
	var <fader1;
	var <button1;
	*new{
		^super.new.initNanoKontrol;
	}
	
	initNanoKontrol{
		faders = IdentityDictionary[
			\fader1 -> 2,
			\fader2 -> 3,
			\fader3 -> 4,
			\fader4 -> 5,
			\fader5 -> 6,
			\fader6 -> 8,
			\fader7 -> 9,
			\fader8 -> 12,
			\fader9 -> 13
		];	
		
		responders = IdentityDictionary.new(faders.size);
		fader1  = NKController.new(2);
		button1 = NKController.new(23);
		
	}
	
	// control is a symbol eg: \fader1, \topButton1, \knob3...
	addResponder{|control, action|
		// if the control is not known throw a warning	
		var ccr = CCResponder({|src, chan, num, vel| 
			action.value(vel);  // to make explicit use of velocity, we pass it as a param
		}, num:faders[control]);
		
		// only 1 responder per control. remove if already there
		if (responders[control] != nil,{
			("removing " ++ control ++ " from responders").postln;
			this.removeResponder(control);
		});
		responders.put(control, ccr);
	}
	
	/*
	doesNotUnderstand { arg selector ... args;
		selector.postln;
		args.postln;
		//DoesNotUnderstandError(this, selector, args).throw;
	}
	*/
	removeResponder{|control|
		responders[control].remove;
		responders.removeAt(control);
	}
	
	// to allow n[\fader1]
	at{|key|
		^faders.at(key)
	}

}

NKController {
	
	var <num;
	var responder;
	
	*new{|n|
		^super.new.initNKController(n);
	}
	
	initNKController{|n|
		num = n;
	}
	
	
	onChanged{|action|
		// remove if already assigned
		if (responder != nil, {responder.remove;});

		responder = CCResponder({|src, chan, num, vel| 
			action.value(vel);  // to make explicit use of velocity, we pass it as a param
		}, num:num);
	
	}

}

/*
NKController {
	
	var <num;
	
	*new{
		^super.new.initNanoKontrol;
	}
	
	onChanged{|action|
		var ccr = CCResponder({|src, chan, num, vel| 
			action.value(vel);  // to make explicit use of velocity, we pass it as a param
		}, num:faders[control]);
		
	}
	
	
}

NKFader : NKController {
	
	onPress{}
	onRelease{}
	onToggle{}
	
}*/