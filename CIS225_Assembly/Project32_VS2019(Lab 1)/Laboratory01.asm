; Sawyer Davis - Laboratory 01
;Date: 02/06/2024
;Due:  02/13/24

COMMENT *
This program manages information for snow blowing business. The program will take the dimensions of the driveway, calculate a square footage,
and then calcuate the number of passes and the time it will take. Using this information an hourly cost and maintnence cost will be applied. 
Lastly a total will be computed.
*

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	dLength word 100
	dWidth word 80
	speed word 3
	widthSB word 18
	rate word 1600
	maintnence word 800
	numPasses word ?
	passTime word ?
	totalTime word  ?
	laborCost word ?
	totalCost word ?

.code
main proc
;Compute Num Passes
	mov edx, 0
	mov ax, dWidth
	mov bx, widthSB
	mov cx, 12
	mul cx
	div bx
	add ax, 1
	mov numPasses, ax

;Compute Pass Time
	mov edx, 0
	mov ax, dLength
	mov bx, speed
	div bx
	add ax, 1
	mov passTime, ax

;Compute Total Time
	mov edx, 0
	mov ax, numPasses
	mov bx, passTime
	mul bx
	mov bx, 60
	div bx
	add ax, 1
	mov totalTime, ax

;Compute Cost of Labor
	mov ax, totalTime
	mov bx, rate
	mul bx
	mov laborCost, ax

;Compute Total Cost
	mov ax, totalTime
	mov bx, maintnence
	mul bx
	add ax, laborCost
	mov totalCost, ax

	invoke ExitProcess,0
main endp
end main