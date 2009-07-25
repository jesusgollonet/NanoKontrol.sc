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
	
	var <controllers;

	*new{
		^super.new.initNanoKontrol;
	}
	
	initNanoKontrol{
		controllers = IdentityDictionary[
			\fader1 -> NKController.new(2),
			\fader2 -> NKController.new(3),
			\fader3 -> NKController.new(4),
			\fader4 -> NKController.new(5),
			\fader5 -> NKController.new(6),
			\fader6 -> NKController.new(8),
			\fader7 -> NKController.new(9),
			\fader8 -> NKController.new(12),
			\fader9 -> NKController.new(13),
			
			\knob1 -> NKController.new(14),
			\knob2 -> NKController.new(15),
			\knob3 -> NKController.new(16),
			\knob4 -> NKController.new(17),
			\knob5 -> NKController.new(18),
			\knob6 -> NKController.new(19),
			\knob7 -> NKController.new(20),
			\knob8 -> NKController.new(21),
			\knob9 -> NKController.new(22),

		];	
		
	}
	
	
	/*
	doesNotUnderstand { arg selector ... args;
		selector.postln;
		args.postln;
		//DoesNotUnderstandError(this, selector, args).throw;
	}
	*/
	
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
		
		if (responder != nil, {responder.remove;}); // remove if already assigned

		responder = CCResponder({|src, chan, num, vel| 
			action.value(vel);  // to make explicit use of velocity, we pass it as a param
		}, num:num);
	
	}

}

NKFader : NKController {
	
	onPress{}
	onRelease{}
	onToggle{}
	
}