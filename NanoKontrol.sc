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
            \fader01 -> NKController(\fader01, 2),
            \fader02 -> NKController(\fader02, 3),
            \fader03 -> NKController(\fader03, 4),
            \fader04 -> NKController(\fader04, 5),
            \fader05 -> NKController(\fader05, 6),
            \fader06 -> NKController(\fader06, 8),
            \fader07 -> NKController(\fader07, 9),
            \fader08 -> NKController(\fader08, 12),
            \fader09 -> NKController(\fader09, 13),
			\fader10 -> NKController(\fader10, 42),
            \fader11 -> NKController(\fader11, 43),
            \fader12 -> NKController(\fader12, 50),
            \fader13 -> NKController(\fader13, 51),
            \fader14 -> NKController(\fader14, 52),
            \fader15 -> NKController(\fader15, 53),
            \fader16 -> NKController(\fader16, 54),
            \fader17 -> NKController(\fader17, 55),
            \fader18 -> NKController(\fader18, 56),
			\fader19 -> NKController(\fader19, 85),
			\fader20 -> NKController(\fader20, 86),
            \fader21 -> NKController(\fader21, 87),
            \fader22 -> NKController(\fader22, 88),
            \fader23 -> NKController(\fader23, 89),
            \fader24 -> NKController(\fader24, 90),
            \fader25 -> NKController(\fader25, 91),
            \fader26 -> NKController(\fader26, 92),
            \fader27 -> NKController(\fader27, 93),
		];
        
        knobgroup= IdentityDictionary[
            \knob01 -> NKController(\knob01, 14),
            \knob02 -> NKController(\knob02, 15),
            \knob03 -> NKController(\knob03, 16),
            \knob04 -> NKController(\knob04, 17),
            \knob05 -> NKController(\knob05, 18),
            \knob06 -> NKController(\knob06, 19),
            \knob07 -> NKController(\knob07, 20),
            \knob08 -> NKController(\knob08, 21),
            \knob09 -> NKController(\knob09, 22),
			\knob10 -> NKController(\knob10, 57),
			\knob11 -> NKController(\knob11, 58),
            \knob12 -> NKController(\knob12, 59),
            \knob13 -> NKController(\knob13, 60),
            \knob14 -> NKController(\knob14, 61),
            \knob15 -> NKController(\knob15, 62),
            \knob16 -> NKController(\knob16, 63),
            \knob17 -> NKController(\knob17, 65),
            \knob18 -> NKController(\knob18, 66),
            \knob19 -> NKController(\knob19, 94),
			\knob20 -> NKController(\knob20, 95),
			\knob21 -> NKController(\knob21, 96),
            \knob22 -> NKController(\knob22, 97),
            \knob23 -> NKController(\knob23, 102),
            \knob24 -> NKController(\knob24, 103),
            \knob25 -> NKController(\knob25, 104),
            \knob26 -> NKController(\knob26, 105),
            \knob27 -> NKController(\knob27, 106),
        ];
        
        topBtsgroup= IdentityDictionary[
            \topBt01 -> NKButton(\topBt01, 23),
            \topBt02 -> NKButton(\topBt02, 24),
            \topBt03 -> NKButton(\topBt03, 25),
            \topBt04 -> NKButton(\topBt04, 26),
            \topBt05 -> NKButton(\topBt05, 27),
            \topBt06 -> NKButton(\topBt06, 28),
            \topBt07 -> NKButton(\topBt07, 29),
            \topBt08 -> NKButton(\topBt08, 30),
            \topBt09 -> NKButton(\topBt09, 31),
			\topBt10 -> NKButton(\topBt10, 67),
            \topBt11 -> NKButton(\topBt11, 68),
            \topBt12 -> NKButton(\topBt12, 69),
            \topBt13 -> NKButton(\topBt13, 70),
            \topBt14 -> NKButton(\topBt14, 71),
            \topBt15 -> NKButton(\topBt15, 72),
            \topBt16 -> NKButton(\topBt16, 73),
            \topBt17 -> NKButton(\topBt17, 74),
            \topBt18 -> NKButton(\topBt18, 75),
			\topBt19 -> NKButton(\topBt19, 107),
			\topBt20 -> NKButton(\topBt20, 108),
            \topBt21 -> NKButton(\topBt21, 109),
            \topBt22 -> NKButton(\topBt22, 110),
            \topBt23 -> NKButton(\topBt23, 111),
            \topBt24 -> NKButton(\topBt24, 112),
            \topBt25 -> NKButton(\topBt25, 113),
            \topBt26 -> NKButton(\topBt26, 114),
            \topBt27 -> NKButton(\topBt27, 115)
        ];

        bottomBtsgroup= IdentityDictionary[
            \bottomBt01 -> NKButton(\bottomBt01, 33),
            \bottomBt02 -> NKButton(\bottomBt02, 34),
            \bottomBt03 -> NKButton(\bottomBt03, 35),
            \bottomBt04 -> NKButton(\bottomBt04, 36),
            \bottomBt05 -> NKButton(\bottomBt05, 37),
            \bottomBt06 -> NKButton(\bottomBt06, 38),
            \bottomBt07 -> NKButton(\bottomBt07, 39),
            \bottomBt08 -> NKButton(\bottomBt08, 40),
            \bottomBt09 -> NKButton(\bottomBt09, 41),
			\bottomBt10 -> NKButton(\bottomBt10, 76),
            \bottomBt11 -> NKButton(\bottomBt11, 77),
            \bottomBt12 -> NKButton(\bottomBt12, 78),
            \bottomBt13 -> NKButton(\bottomBt13, 79),
            \bottomBt14 -> NKButton(\bottomBt14, 80),
            \bottomBt15 -> NKButton(\bottomBt15, 81),
            \bottomBt16 -> NKButton(\bottomBt16, 82),
            \bottomBt17 -> NKButton(\bottomBt17, 83),
            \bottomBt18 -> NKButton(\bottomBt18, 84),
            \bottomBt19 -> NKButton(\bottomBt19, 116),
			\bottomBt20 -> NKButton(\bottomBt20, 117),
            \bottomBt21 -> NKButton(\bottomBt21, 118),
            \bottomBt22 -> NKButton(\bottomBt22, 119),
            \bottomBt23 -> NKButton(\bottomBt23, 120),
            \bottomBt24 -> NKButton(\bottomBt24, 121),
            \bottomBt25 -> NKButton(\bottomBt25, 122),
            \bottomBt26 -> NKButton(\bottomBt26, 123),
            \bottomBt27 -> NKButton(\bottomBt27, 124)
        ];
        
        transportBtsgroup= IdentityDictionary[
            \playBt   -> NKButton(\playBt,   45),
            \stopBt   -> NKButton(\stopBt,   46),
            \recBt    -> NKButton(\recBt,    44),
            \rewindBt -> NKButton(\rewindBt, 47),
            \ffwBt    -> NKButton(\ffwBt,    48),
            \loopBt   -> NKButton(\loopBt,   49)
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

    *new{|... args|
        ^super.newCopyArgs(*args);
    }

    onChanged_{|action| 
        responder= MIDIdef.cc(key, {|val| action.value(val) }, num);
    }
    
    free{
        responder.free;
    }
}

NKButton : NKController {

    var pressresp, releaseresp;

    onPress_{|action|
        pressresp= MIDIdef.cc((key++"press").asSymbol, {|val| if(val==127, { action.value(val) }) }, num);
    }

    onRelease_{|action|
        releaseresp= MIDIdef.cc((key++"release").asSymbol, {|val| if(val==0, { action.value(val) }) }, num);
    }

    free{
        pressresp.free;
        releaseresp.free;
    }
}
