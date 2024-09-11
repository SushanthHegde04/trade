/**
 * 
 */
function userdata(){
let UserId=sessionStorage.getItem("UserId");
document.querySelector("#uid").innerText="user_id: "+sessionStorage.getItem("UserId");

let UserEmail="";

fetch(`users/name/${UserId}`,{
	method:"GET",
	   headers: {
            "Content-Type": "application/json" 
        }
}).then((data)=>
{
	  if (!data.ok) {
                throw new Error('Network response was not ok');
            }
	return data.json();
}).then((Userdata)=>{
	
	console.log(Userdata);
 // Update the username
    document.querySelector("#uname").innerText = "User Name: " + Userdata.uName;

    // Update the email
    document.querySelector("#uemail").innerText = "Email ID: " + Userdata.email;
document .querySelector('#uinvested').innerText=Userdata.invested;
document.querySelector("#ubalance").innerText=Userdata.balance;

})

.catch((err)=>
{
	console.log("error")
})

}
userdata();


