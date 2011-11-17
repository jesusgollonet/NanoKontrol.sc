/*
    update for sc 3.5
    now supports collection.do for incremental assignment

	TODO: 
		- implement scenes
*/

NanoKontrol {

	var fadergroup, knobgroup, topBtsgroup, bottomBtsgroup, transportBtsgroup;
	var <controllers;

    *new{
        ^super.new.initNanoKontrol;
    }

    initNanoKontrol{

        MIDIIn.connectAll;

		fadergroup= IdentityDictionary[
			\fader1 -> NKController(\fader1, 2),
			\fader2 -> NKController(\fader2, 3),
			\fader3 -> NKController(\fader3, 4),
			\fader4 -> NKController(\fader4, 5),
			\fader5 -> NKController(\fader5, 6),
			\fader6 -> NKController(\fader6, 8),
			\fader7 -> NKController(\fader7, 9),
			\fader8 -> NKController(\fader8, 12),
			\fader9 -> NKController(\fader9, 13)
		];
		
		knobgroup= IdentityDictionary[
			\knob1 -> NKController(\knob1, 14),
			\knob2 -> NKController(\knob2, 15),
			\knob3 -> NKController(\knob3, 16),
			\knob4 -> NKController(\knob4, 17),
			\knob5 -> NKController(\knob5, 18),
			\knob6 -> NKController(\knob6, 19),
			\knob7 -> NKController(\knob7, 20),
			\knob8 -> NKController(\knob8, 21),
			\knob9 -> NKController(\knob9, 22)
		];
		
		topBtsgroup= IdentityDictionary[
			\topBt1 -> NKController(\topBt1, 23),
			\topBt2 -> NKController(\topBt2, 24),
			\topBt3 -> NKController(\topBt3, 25),
			\topBt4 -> NKController(\topBt4, 26),
			\topBt5 -> NKController(\topBt5, 27),
			\topBt6 -> NKController(\topBt6, 28),
			\topBt7 -> NKController(\topBt7, 29),
			\topBt8 -> NKController(\topBt8, 30),
			\topBt9 -> NKController(\topBt9, 31)
		];

		bottomBtsgroup= IdentityDictionary[
			\bottomBt1 -> NKController(\bottomBt1, 33),
			\bottomBt2 -> NKController(\bottomBt2, 34),
			\bottomBt3 -> NKController(\bottomBt3, 35),
			\bottomBt4 -> NKController(\bottomBt4, 36),
			\bottomBt5 -> NKController(\bottomBt5, 37),
			\bottomBt6 -> NKController(\bottomBt6, 38),
			\bottomBt7 -> NKController(\bottomBt7, 39),
			\bottomBt8 -> NKController(\bottomBt8, 40),
			\bottomBt9 -> NKController(\bottomBt9, 41)
		];
		
		transportBtsgroup= IdentityDictionary[
			\playBt   -> NKController(\playBt,   45),
			\stopBt   -> NKController(\stopBt,   46),
			\recBt    -> NKController(\recBt,    44),
			\rewindBt -> NKController(\rewindBt, 47),
			\ffwBt    -> NKController(\ffwBt,    48),
			\loopBt   -> NKController(\loopBt,   49)
		];	

		controllers= IdentityDictionary.new;
		controllers.putAll(fadergroup, knobgroup, topBtsgroup, bottomBtsgroup, transportBtsgroup);
    }

    faders{
        var align= fadergroup; 
        align.order;
        ^align.atAll(align.order);
    }

    knobs{
        var align= knobgroup;
        align.order;
        ^align.atAll(align.order);
    }

    topBts{
        var align= topBtsgroup;
        align.order;
        ^align.atAll(align.order);
    }

    bottomBts{
        var align= bottomBtsgroup;
        align.order;
        ^align.atAll(align.order);
    }

    transportBts{
        var align= transportBtsgroup;
        align.order;
        ^align.atAll(align.order);
    }

    removeAll{
        controllers.do{|cDict|
            cDict.do{|c| c.free }
        }
    }

	doesNotUnderstand {|selector ... args|	
		var controller = controllers[selector];
		^controller ?? { super.doesNotUnderstand(selector, args) };
	}
}

NKController {
	
    var <key, <num;
    var responder;
    var pressresp, releaseresp;

    *new{|... args|
        ^super.newCopyArgs(*args);
    }

	onChanged_{|action| 
        responder= MIDIdef.cc(key, {|val| action.value(val) }, num);
	}
	
	onPress_{|action|
        pressresp= MIDIdef.cc(key ++ "press", {|val| if(val==127, { action.value(val) }) }, num);
	}

	onRelease_{|action|
        releaseresp= MIDIdef.cc(key ++ "release", {|val| if(val==0, { action.value(val) }) }, num);
	}

    free{
        [ responder, pressresp, releaseresp ].do(_.free);
    }
}
