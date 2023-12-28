var pieceRanges = []
function Piece(centerX, centerY, color) {
    this.centerX = centerX;
    this.centerY = centerY;
    this.color = color;
}
document.addEventListener('mousemove', function(e) {
    var x = e.clientX;
    var y = e.clientY;
    document.getElementById('coordinates').textContent = 'Mouse position: ' + x + ', ' + y;
	getCanvasIndex();
});

function getCanvasIndex(){
	var canvas = document.getElementById('chessboard');
	var rect = canvas.getBoundingClientRect();
	document.getElementById('canvasIndex').textContent = 'canvas Index: ' + rect.left + ', ' + rect.top;
}
