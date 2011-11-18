NanoKontrol
===========

simple use of the Korg NanoKontrol with SuperCollider

Installation
------------
put a copy of NanoKontrol.sc in your SuperCollider extensions dir

Usage  
-----

create an instance

    n = NanoKontrol.new

assign actions

    n.fader1.onChanged = {|val| 
        "fader 1 changed".postln;
        val.postln;
    };

    n.topBt1.onPress   = {|val| "top button 1 pressed".postln; val.postln };
                          
    n.topBt1.onRelease = {|val| "top button 1 released".postln; val.postln };

    n.knob1.onChanged  = {|val| "knob 1 changed".postln; val.postln };

actions will be overwritten when assigned to the same item

    n.knob1.onChanged  = {|val| "knob 1 changed".postln; (val/127).postln };

you can assign multiple items directly

faders 1-9

    n.faders.do{|fader, i| 
        fader.onChanged= {|val| ("fader"+(i+1)).postln; val.postln }
    };

assign knobs 1-4 only

    n.knobs[..3].do{|knob| knob.onChanged= {|val| val.postln } };

remove all MIDI responders

    n.removeAll;

Controller names
----------------

* `fader1..9`
* `knob1..9`
* `topBt1..9`
* `bottomBt1..9`

transport buttons 

* `playBt`
* `stopBt`
* `recBt`
* `loopBt`
* `loopBt`
* `rewindBt`
* `ffwBt`

Actions 
-------
* `onChanged` can be applied to all controller names
* `onPress`, `onRelease` only to buttons
* all actions receive the control value as an argument 
