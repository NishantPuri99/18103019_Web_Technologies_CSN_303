//To Run, Open A4.html

/*
Write  a  JavaScript  program  to  generate the  Christmas  Tree  pattern  below.  
The  tree  should  be composed of zeroes (0), and it must be topped with an asterisk (*).
*/
//Reads number of rows to be printed
var Tree_Depth = prompt("Enter the height of the tree(should be greater than equal to 2): " );
 
for(i=1; i<=Tree_Depth; i++)
{ 
    //Prints trailing spaces
    for(j=i; j<Tree_Depth; j++)
    {
        document.write(" ");
    }

    //Prints the pyramid pattern
    for(j=1; j<=(2*i-1); j++)
    {
        if(i==1){
            document.write("*");//Star At Top
        }else{
            document.write("0");//Tree is printed using this
        }
    }

    document.write("<br>");
}