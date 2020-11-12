//To Run, Open A3.html

/*
You're  working  with  an  intern  that  keeps  coming  to  you  with  JavaScript  code  
that  won't  run because  the  braces,  brackets,  and  parentheses  are off. 
To  save  you  both  some  time,  you  decide to write  a  braces/brackets/parentheses  validator. 
Write  an  efficient  function  that  tells  us  whether  or not 
an input string's openers and closers are properly nested.

Examples:
    "{ [ ] ( ) }" should return true
    "{ [ ( ] ) }" should return false
    "{ [ }" should return false
*/
var inputString = prompt("Enter any string containing {,(,[,],)or }:");
var bracketMatching = function (str) 
{

    var stack = [];
    var map = 
    {
        '(': ')',
        '[': ']',
        '{': '}'
    }

    for (let i = 0; i < str.length; i++) 
    {
        // Push Opening Brace to stack
        if (str[i] === '(' || str[i] === '{' || str[i] === '[' ) 
        {
            stack.push(str[i]);
        }
        // Pop Closing Brace
        else 
        {
            let last = stack.pop();
            //If the popped element from the stack,  doesn’t match the corresponding 
            // closing bracket in the map, then return false
            if (str[i] !== map[last]) 
            {
                return "Not Nested Properly";
            }
        }
    }
    //After completing string, if stack is not empty then it is un balanced
    if (stack.length !== 0) 
    {
        return "Not Nested Properly";
    }
    
    // if all above checks are satisfied, return true
    return "Nested Properly";
}
console.log(inputString+" is "+bracketMatching(inputString));