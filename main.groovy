//! Simulation: Main


import org.arl.fjage.*
import org.arl.unet.*
import org.arl.unet.phy.*
import org.arl.unet.sim.*
import org.arl.unet.sim.channels.*
import static org.arl.unet.Services.*
import static org.arl.unet.phy.Physical.*

platform = RealTimePlatform

simulate {
  node '1', address: 1, location: [0, 0, 0], shell: true
  node '2', address: 2, location: [1.km, 0, 0]
  node '3', address: 3, location: [3.km, 0, 0]
  node '4', address: 3, location: [4.km, 0, 0]
  node '5', address: 3, location: [5.km, 0, 0]
  
  node '6', address: 3, location: [5.km, 2.km, 0]
  
  node '7', address: 3, location: [2.km, 6.km, 0]
  
  node '8', address: 3, location: [5.km, 6.km, 0]
  
}